package com.bank.authorization.util;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BeanUtil implements ApplicationContextAware, BeanPostProcessor {
    private static ApplicationContext context;
    private static ConfigurableListableBeanFactory beanFactory;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanUtil.context = applicationContext;
        beanFactory = (ConfigurableListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        log.info("Контекст успешно установлен BeanUtil.class");
    }

    @NotNull
    public static <T> T getBean(Class<T> beanClass) {
        log.info("Получен бин указанного типа BeanUtil.class");
        try {
            return beanFactory.getBean(beanClass);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }
}