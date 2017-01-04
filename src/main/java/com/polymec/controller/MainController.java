package com.polymec.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.MediaType;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polymec.domain.Famille;
import com.polymec.service.FamilleService;
import com.polymec.domain.ArticleInfo;
import com.polymec.service.ArticleInfoService;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.polymec.domain.ArticleAct;
import com.polymec.service.ArticleActService;

/**
 * <p>
 * Controller for all examples contained in the sample.
 *
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping("/main")
public class MainController {

    private Logger log = LoggerFactory.getLogger("com.polymec.controller.MainController");
    
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
    @PostMapping(value = {"", "/", "/index"})
    public String main(@ModelAttribute Famille famille) {

        if (hasRole("ROLE_ADMIN")) {
            return "redirect:/admin/index";
        }

        if (hasRole("ROLE_MANAGER")) {
            return "redirect:/manager/index";
        }

        if (hasRole("ROLE_USER")) {
            return "redirect:/user/index";
        }

        return "redirect:/main/index";
    }

    //POST-REDIRECT-GET Design Pattern applied here
    @GetMapping(value = {"", "/", "/index"})
    public String index(@ModelAttribute Famille famille) {
        log.info("this is index method");
        return "main/index";
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

    // Module liste des articles
    @GetMapping(path = "/articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ArticleInfo> listArticles() {

        List<ArticleInfo> arts = this.articleInfoService.listArticles();

        return arts;
    }
    
    
    // Module Fiche Article
    @GetMapping("/fiche_article/{artId}")
    public ModelAndView ficheArticle(@PathVariable Long artId) {

        //log.info("Print Article id : " + artId);
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

/*
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }


    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        model.addAttribute("errorCode", "Error " + request.getAttribute("javax.servlet.error.status_code"));
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("<ul>");
        while (throwable != null) {
            errorMessage.append("<li>").append(HtmlEscape.escapeHtml5(throwable.getMessage())).append("</li>");
            throwable = throwable.getCause();
        }
        errorMessage.append("</ul>");
        model.addAttribute("errorMessage", errorMessage.toString());
        return "error";
    }

    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }
*/
}
