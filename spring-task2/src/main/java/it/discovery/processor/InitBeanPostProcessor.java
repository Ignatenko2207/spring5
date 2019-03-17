package it.discovery.processor;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class InitBeanPostProcessor implements BeanPostProcessor {
    private final ApplicationContext context;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Stream.of(bean.getClass().getDeclaredMethods())
                .filter(method -> method.getDeclaredAnnotation(Init.class) != null)
                .forEach(method -> {
                    try {
                        if (method.getParameterCount() == 0) {
                            method.invoke(bean);
                        } else if (method.getParameterCount() == 1 &&
                                method.getParameterTypes()[0] == ApplicationContext.class) {
                            method.invoke(bean, context);
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
