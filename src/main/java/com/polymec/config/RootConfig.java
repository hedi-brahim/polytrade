package com.polymec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfig.class, JpaConfig.class})
@ComponentScan(basePackages = {"com.polymec.service", "com.polymec.repository"})
public class RootConfig {
}
