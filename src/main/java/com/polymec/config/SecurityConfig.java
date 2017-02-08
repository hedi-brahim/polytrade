/*
 * Copyright 2017 Pivotal Software, Inc..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.polymec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author Hedi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index","/resources/**","/webjars/**","/articles/**").permitAll() 
                //.antMatchers("/","/index","/resources/**","/webjars/**","/articles/**","/clients/**","/fournisseurs/**","/familles/**","/fiche_article/**","/fiche_client/**","/fiche_fournisseur/**").permitAll()                 
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/manager/**").hasRole("MANAGER")                
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()                
                .formLogin()
                .loginPage("/index")
                .successForwardUrl("/login-success")
                //.failureUrl("/login-error")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("hedi").password("goodluck").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("hedi").password("1577").roles("MANAGER");  
        auth.inMemoryAuthentication().withUser("ramzi").password("2016").roles("USER");  
        auth.inMemoryAuthentication().withUser("rami").password("2016").roles("USER");          
        auth.inMemoryAuthentication().withUser("feten").password("2016").roles("USER");          
        //auth.userDetailsService(userDetailsService);
    }
    
    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
    */
}
