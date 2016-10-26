package com.polymec.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.joran.spi.JoranException;
//import ch.qos.logback.core.status.OnConsoleStatusListener;
//import ch.qos.logback.core.status.StatusManager;

import ch.qos.logback.classic.ViewStatusMessagesServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		/*********************   Contexts     ***********************/		
		// Register the Root application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootConfig.class);

		// Register the Web application context
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebConfig.class);

		
		
		/*********************   Listners     ***********************/		
		// Context loader listener
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		
		
		/*********************   Filters     ***********************/
		// Register the Dandelion filter
		FilterRegistration.Dynamic dandelionFilter = servletContext.addFilter("dandelionFilter", new DandelionFilter());
		dandelionFilter.addMappingForUrlPatterns(null, false, "/*");

		
		
		/*********************   Servlets     ***********************/		
		// Spring dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("springServlet", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		// Dandelion servlet
		ServletRegistration.Dynamic dandelionServlet = servletContext.addServlet("dandelionServlet",new DandelionServlet());
		dandelionServlet.setLoadOnStartup(2);
		dandelionServlet.addMapping("/dandelion-assets/*");
		
		// LogBack ViewStatusMessages servlet
		// The ViewStatusMessages servlet will be viewable at the URL http://localhost:9090/polytrade/lbClassicStatus
		ServletRegistration.Dynamic logbackServlet = servletContext.addServlet("ViewStatusMessages",new ViewStatusMessagesServlet());
		logbackServlet.setLoadOnStartup(2);
		logbackServlet.addMapping("/lbClassicStatus");		
	}
}
