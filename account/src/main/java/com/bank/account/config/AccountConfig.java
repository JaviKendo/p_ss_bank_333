package com.bank.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * Класс конфигурации аккаунта.
 * Определяет конфигурацию для аккаунта, включая JPA аудитинг.
 */
@Configuration
@EnableJpaAuditing
public class AccountConfig {

}
