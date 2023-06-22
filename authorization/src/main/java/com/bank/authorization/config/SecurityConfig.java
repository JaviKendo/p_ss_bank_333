package com.bank.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Конфигурирует доступ к ресурсам для пользователей.
     *
     * @param http HttpSecurity объект для настройки доступа к ресурсам.
     * @throws Exception исключение, возникающее при настройке доступа к ресурсам.
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // разрешить доступ к Swagger UI без аутентификации
                .antMatchers("/swagger-ui.html", "/swagger-ui/").permitAll()
                .antMatchers("/swagger-resources/**", "/api/authorizations/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /**
     * Конфигурирует авторизацию пользователей.
     *
     * @param auth AuthenticationManagerBuilder объект для настройки авторизации пользователей.
     * @throws Exception исключение, возникающее при настройке авторизации пользователей.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("users")
                .password("{noop}password")
                .roles("USER");
    }
}
