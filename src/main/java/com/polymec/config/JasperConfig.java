package com.polymec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

import com.github.dandelion.datatables.thymeleaf.dialect.DataTablesDialect;
import com.github.dandelion.thymeleaf.dialect.DandelionDialect;

@Configuration
public class JasperConfig {

	@Bean
	public ResourceBundleViewResolver getResourceBundleViewResolver() {
	  ResourceBundleViewResolver resolver = new ResourceBundleViewResolver();
	  resolver.setBasename("jasperreport-views");
	  return resolver;
	}
	
}
