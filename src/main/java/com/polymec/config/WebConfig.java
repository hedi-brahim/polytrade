package com.polymec.config;

import com.polymec.domain.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import org.springframework.http.CacheControl;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import org.springframework.core.convert.converter.Converter; 

@Configuration
@ComponentScan(basePackages = {"com.polymec.controller"})
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
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getStringToUserRole());
        //registry.addConverter(new LocalDateTimeConverter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
    }
    
/*
    public Converter<String, EmailAddress> getStringToEmailAddressConverter() {
        return new Converter<String, EmailAddress>() {
            @Override
            public EmailAddress convert(String source) {
                EmailAddress emailAddress = new EmailAddress();
                emailAddress.setAddress(source);
                return emailAddress;
            }
        };
    }
*/
    public Converter<String, UserRole> getStringToUserRole() {
        return new Converter<String, UserRole>(){
            @Override
            public UserRole convert(String s){
                UserRole ur = new UserRole();
                ur.setRole(s);
                return ur;
            }
        };
    }

  /*  
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");    
        //registry.addViewController("/main").setViewName("index");
    
        registry.addViewController("/").setViewName("main");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("main");
        registry.addViewController("/403").setViewName("403");
    
    }
*/    
    
}
