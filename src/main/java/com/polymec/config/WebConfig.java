package com.polymec.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import org.springframework.http.CacheControl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@ComponentScan(basePackages = {"com.polymec.web"})
@EnableWebMvc
@Import({JasperConfig.class, ThymeleafConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger("com.polymec.config.WebConfig");

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

        if (!registry.hasMappingForPattern("/resources/**")) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("/WEB-INF/resources/")
                    .setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
        }

    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
    }    

}
