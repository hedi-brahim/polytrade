package com.polymec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ JpaConfig.class })
@ComponentScan(basePackages = { "com.polymec.service", "com.polymec.repository" })
public class RootConfig {
}
