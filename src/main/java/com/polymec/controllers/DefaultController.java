package com.polymec.controllers;

<<<<<<< HEAD
import com.polymec.domain.ArticleAct;
import com.polymec.domain.ClientAct;
import com.polymec.domain.ArticleInfo;
import com.polymec.domain.Client;
import com.polymec.domain.Famille;
import com.polymec.domain.Fournisseur;
import com.polymec.services.ArticleActService;
import com.polymec.services.ClientActService;
import com.polymec.services.ArticleInfoService;
import com.polymec.services.ClientService;
import com.polymec.services.FamilleService;
import com.polymec.services.FournisseurService;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
=======
import com.polymec.domain.ArticleInfo;
import com.polymec.services.ArticleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;
>>>>>>> develop

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
=======
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> develop

/**
 * <p>
 * Controller for all examples contained in the sample.
 *
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping("/")
public class DefaultController {

<<<<<<< HEAD
    private Logger log = LoggerFactory.getLogger("com.polymec.controllers.MainController");
    
    @Autowired
    private ArticleInfoService articleInfoService;

    @Autowired
    private FamilleService familleService;
    
    @Autowired
    private ClientService clientService;

    @Autowired
    private FournisseurService fournisseurService;    
    
    @Autowired
    private ArticleActService articleActService;
    
    @Autowired
    private ClientActService clientActService;
    

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
=======
    private Logger log = LoggerFactory.getLogger("com.polymec.controllers.DefaultController");

    @Autowired
    private ArticleInfoService articleInfoService;

    private boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = 
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext().
                        getAuthentication().getAuthorities();
>>>>>>> develop
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }
<<<<<<< HEAD

   // Module selection du role utilisateur
    @PostMapping(value = {"/login-success"})
    public String main(@ModelAttribute Famille famille) {
        log.info("this is postindex method");
        
=======
    
    @RequestMapping(value = {"", "/index", "/login-success"})
    public String index() {
        log.info("this is RequestMapping index method");

>>>>>>> develop
        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }

        if (hasRole("ROLE_MANAGER")) {
            return "redirect:/manager/index";
        }

        if (hasRole("ROLE_USER")) {
            return "redirect:/user/index";
        }
<<<<<<< HEAD

        return "redirect:/index";
    }

    //POST-REDIRECT-GET Design Pattern applied here
    @GetMapping(value = {""})
    public String index() {
       log.info("this is a redirect get index method");
        return "redirect:/index";
    }
    
 
    @GetMapping(value = {"/index"})
    public String index(@ModelAttribute Famille famille) {
        log.info("this is get index method");
        return "/index";
    }
    
=======
        
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

>>>>>>> develop
    // Module liste des articles
    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ArticleInfo> listArticles() {

        List<ArticleInfo> arts = this.articleInfoService.listArticles();

        return arts;
    }
<<<<<<< HEAD
    
    // Module liste des clients
    @GetMapping(path = "/clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Client> listClients() {

        List<Client> clts = this.clientService.listClients();

        return clts;
    }
    
    // Module liste des fournisseurs
    @GetMapping(path = "/fournisseurs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Fournisseur> listFournisseurs() {

        List<Fournisseur> frs = this.fournisseurService.listFournisseurs();

        return frs;
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
    
    
    // Module Fiche Article
    @GetMapping(value = {"fiche_article/{artId}"})
    public ModelAndView ficheArticle(@PathVariable Long artId) {

        log.info("Print Article id : " + artId);
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        // pass article infos to jasper reports
        ArticleInfo art = this.articleInfoService.findArticleInfoById(artId);
        parameterMap.put("reference", art.getReference());
        parameterMap.put("designation", art.getDesignation());
        parameterMap.put("quantite", art.getQuantite());
        parameterMap.put("puaht", art.getPuaht());
        parameterMap.put("puvht", art.getPuvht());

        // pass list of article acts to jasper reports
        List<ArticleAct> arts = this.articleActService.listArticleActs(artId);
        Collections.sort(arts);
        JRDataSource jrDS = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", jrDS);

        return new ModelAndView("ficheArticle", parameterMap);

    }    
    
    // Module Fiche Client
    @GetMapping(value = {"fiche_client/{cltId}"})
    public ModelAndView ficheClient(@PathVariable Long cltId) {

        log.info("Print Client id : " + cltId);
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        // pass article infos to jasper reports
        Client clt = this.clientService.findById(cltId);
        parameterMap.put("raison", clt.getRaison());
        
       log.info("Print Client raison : " + clt.getRaison());
       
        // pass list of article acts to jasper reports
        
        List<ClientAct> acts = this.clientActService.listClientActs(cltId);
        Collections.sort(acts);
        JRDataSource jrDS = new JRBeanCollectionDataSource(acts);
        parameterMap.put("datasource", jrDS);
        
        return new ModelAndView("ficheClient", parameterMap);

    }     
=======

>>>>>>> develop
}
