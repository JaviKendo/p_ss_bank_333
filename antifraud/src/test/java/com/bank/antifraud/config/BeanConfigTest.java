package com.bank.antifraud.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BeanConfigTest {

    @Test
    void testObjectMapper() {
        BeanConfig beanConfig = new BeanConfig();

        assertThat(beanConfig.objectMapper().getClass()).isEqualTo(ObjectMapper.class);
    }

}