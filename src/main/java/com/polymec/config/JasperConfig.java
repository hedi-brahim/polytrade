package com.polymec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
public class JasperConfig {

    @Bean
    public ResourceBundleViewResolver getResourceBundleViewResolver() {
        ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
        resolver.setBasename("jasperreport-views");
        resolver.setOrder(0);        
        return resolver;
    }

}
