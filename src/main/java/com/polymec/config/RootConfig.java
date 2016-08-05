package com.polymec.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.polymec.service", "com.polymec.repository" })
public class RootConfig {
}
