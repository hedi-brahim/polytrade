package com.polymec.controllers;

import com.polymec.domain.ArticleInfo;
import com.polymec.domain.Famille;
import com.polymec.services.ArticleActService;
import com.polymec.services.ArticleInfoService;
import com.polymec.services.FamilleService;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * Controller for all examples contained in the sample.
 *
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    private Logger log = LoggerFactory.getLogger("com.polymec.controllers.MainController");
    
    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private FamilleService familleService;
    
    @Autowired
    private ArticleActService articleActService;

    @ModelAttribute("familles")
    public List<Famille> populateallFamille() {

        Famille fml = new Famille("STOCK TOTAL");
        List<Famille> fmls = new ArrayList<Famille>();
        fmls.add(fml);
        fmls.addAll(this.familleService.findAllValid());

        return fmls;
    }
    
    @ModelAttribute("articlesExistants")
    public List<ArticleInfo> listArticlesExistants() {
        return this.articleInfoService.listArticlesExistants();
    }
    
    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

   // Module selection du role utilisateur
    @PostMapping(value = {"/login-success"})
    public String main(@ModelAttribute Famille famille) {
        log.info("this is postindex method");
        
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
    @GetMapping(value = {"","/index"})
    public String index(@ModelAttribute Famille famille) {
        log.info("this is get index method");
        return "/index";
    }

        // Module liste des articles
    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ArticleInfo> listArticles() {

        List<ArticleInfo> arts = this.articleInfoService.listArticles();

        return arts;
    }
    
    // Module liste des familles
    @PostMapping("/familles")
    public ModelAndView getFamilles(@RequestParam("id") Long id) {

        log.info("Print Famille id : " + id);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        if (id == null) {
            return new ModelAndView("ficheStock", parameterMap);
        } else {
            List<ArticleInfo> arts = this.articleInfoService.findByFamille(id);

            JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
            parameterMap.put("datasource", JRdataSource);

            return new ModelAndView("ficheFamille", parameterMap);
        }
    }    
}