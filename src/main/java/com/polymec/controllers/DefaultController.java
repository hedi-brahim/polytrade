package com.polymec.controllers;

import com.polymec.domain.ArticleInfo;
import com.polymec.services.ArticleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * Controller for all examples contained in the sample.
 *
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    private Logger log = LoggerFactory.getLogger("com.polymec.controllers.DefaultController");

    @Autowired
    private ArticleInfoService articleInfoService;

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = 
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().
                        getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
    
    @RequestMapping(value = {"", "/index", "/login-success"})
    public String index() {
        log.info("this is RequestMapping index method");

        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }

        if (hasRole("ROLE_MANAGER")) {
            return "redirect:/manager/index";
        }

        if (hasRole("ROLE_USER")) {
            return "redirect:/user/index";
        }
        
        return "redirect:/index";
    }

    //POST-REDIRECT-GET Design Pattern applied here    
    @GetMapping(value = {"/index"})
    public String RedirectIndex() {
        
        log.info("this is GetMapping index method");
        
        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }

        if (hasRole("ROLE_MANAGER")) {
            return "redirect:/manager/index";
        }

        if (hasRole("ROLE_USER")) {
            return "redirect:/user/index";
        }
        
        return "index";
    }

    // Module liste des articles
    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ArticleInfo> listArticles() {

        List<ArticleInfo> arts = this.articleInfoService.listArticles();

        return arts;
    }

}
