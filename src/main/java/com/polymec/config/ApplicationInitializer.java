package com.polymec.config;

import ch.qos.logback.classic.ViewStatusMessagesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        /**
         * ******************* Contexts     **********************
         */
        // Register the Root application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(RootConfig.class);

        // Register the Web application context
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfig.class);

        /**
         * ******************* Listners     **********************
         */
        // Context loader listener
        servletContext.addListener(new ContextLoaderListener(rootContext));

        /**
         * ******************* Servlets     **********************
         */
        // Spring dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("springServlet", new DispatcherServlet(webContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // LogBack ViewStatusMessages servlet
        // The ViewStatusMessages servlet will be viewable at the URL http://localhost:9090/polytrade/lbClassicStatus
        ServletRegistration.Dynamic logbackServlet = servletContext.addServlet("ViewStatusMessages", new ViewStatusMessagesServlet());
        logbackServlet.setLoadOnStartup(2);
        logbackServlet.addMapping("/lbClassicStatus");
    }
}
