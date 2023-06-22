package com.bank.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(modifyOnCreate = false)
public class AuditorConfig {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                try {
                    final Process process = Runtime.getRuntime().exec("git config user.name");
                    process.waitFor();
                    return Optional.of(
                            new BufferedReader(new InputStreamReader(process.getInputStream())).readLine()
                    );
                } catch (Exception e) {
                    try {
                        return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
                    } catch (Exception ex) {
                        return Optional.of("Пользователь");
                    }
                }
            }
        };
    }
}
