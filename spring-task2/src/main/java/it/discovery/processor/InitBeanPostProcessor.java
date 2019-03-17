package it.discovery.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@RequiredArgsConstructor
public class InitBeanPostProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    protected <T> T getTargetObject(Object proxy) {
        try {
            while ((AopUtils.isJdkDynamicProxy(proxy))) {
                return (T) getTargetObject(((Advised) proxy).getTargetSource().getTarget());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return (T) proxy; // expected to be cglib proxy then, which is simply a specialized class
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = bean.getClass();
        Object targetObject = bean;
        if (AopUtils.isAopProxy(bean) || AopUtils.isCglibProxy(bean)) {
            targetClass = AopUtils.getTargetClass(bean);
            targetObject = getTargetObject(bean);
        }
        Object finalObject = targetObject;
        Arrays.stream(targetClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Init.class))
                .forEach(method -> {
                    try {
                        if (method.getParameterCount() == 0) {
                            method.invoke(finalObject);
                        } else if (method.getParameterCount() == 1 &&
                                method.getParameterTypes()[0] == ApplicationContext.class) {
                            method.invoke(finalObject, context);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        return bean;
    }
}
