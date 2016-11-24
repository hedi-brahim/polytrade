package com.polymec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.polymec.service.JPAUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    
    @Autowired
    private JPAUserDetailsService userDetailsService;
    
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("hedi").password("goodluck").roles("ADMIN");     
        auth.userDetailsService(userDetailsService);
    }
      
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**","/webjars/**","/main/**").permitAll() 
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()                
                .formLogin()
                .loginPage("/main")
                .successForwardUrl("/main")
                //.failureUrl("/login-error")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");

    }

/*   
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     */
}
