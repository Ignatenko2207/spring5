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
                // .filter(method -> method.getParameters().length == 0)
                .forEach(method -> {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        return bean;
    }
}
