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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.polymec.domain.Famille;
import com.polymec.service.FamilleService;
import com.polymec.domain.ArticleInfo;
import com.polymec.service.ArticleInfoService;
import com.polymec.domain.ArticleFrns;
import com.polymec.service.ArticleFrnsService;
import com.polymec.domain.InventaireArticle;
import com.polymec.domain.Credit;
import com.polymec.service.InventaireArticleService;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.unbescape.html.HtmlEscape;
import com.polymec.dao.CreditRepository;

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

    //private String artRef;
    
    @Autowired
    private ArticleFrnsService articleFrnsService;

    @Autowired
    private FamilleService familleService;

    @Autowired
    private InventaireArticleService inventaireArticleService;

    @Autowired
    private ArticleInfoService ArticleInfoService;

    @ModelAttribute("allFamille")
    public List<Famille> populateallFamille() {

        Famille fml = new Famille("STOCK TOTAL");
        List<Famille> fmls = new ArrayList<Famille>();
        fmls.add(fml);
        fmls.addAll(this.familleService.findAllValid());

        return fmls;
    }

    @ModelAttribute("allArticleFrns")
    public List<ArticleInfo> populateArticleFrns() {
        return this.articleFrnsService.findAllValid();
    }

    
    @RequestMapping(value = {"", "/"})
    public String main(@ModelAttribute Famille famille) {
        
        if (hasRole("ROLE_ADMIN")) {  
            //POST-REDIRECT-GET Design Pattern applied here
            return "redirect:/admin/index";
        }
        
        if (hasRole("ROLE_MANAGER")) {
            //POST-REDIRECT-GET Design Pattern applied here
            return "redirect:/manager/index";
        }

        if (hasRole("ROLE_USER")) {
            //POST-REDIRECT-GET Design Pattern applied here
            return "redirect:/user/index";
        }

        return "main";
    }    
/*
    @RequestMapping("/logout")
    public String logout(@ModelAttribute Famille famille) {

        return "index";
    }
    */
    /*
	@ModelAttribute("allArticleFrns")
	@ResponseBody
    public List<ArticleFrns> populateArticleFrns() {
        return this.articleFrnsService.findAllValid();
    }	
     */
    @PostMapping("/familleReport")
    public ModelAndView getArticlesReport(@RequestParam("id") Long id) {

        log.info("Print Famille id : " + id);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        if (id == null) {
            return new ModelAndView("articlesReport", parameterMap);
        } else {
            List<ArticleFrns> arts = this.articleFrnsService.findByFamille(id);
            JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
            parameterMap.put("datasource", JRdataSource);

            return new ModelAndView("familleReport", parameterMap);
        }
    }

    /*
	@GetMapping("/inventaire/{lartRef}")
	public String getInventaireArticle(@PathVariable String lartRef) {
		artRef = lartRef;
		logger.info("Print Method GetInventaireArticle");
		return "/pages/inventaire";
	}	
     */
    /**
     * Home page.
     */
    /*
    @RequestMapping("/index")
    public String index(@ModelAttribute Famille famille) {
        return "index";
    }
     */
    
    @RequestMapping("/index")
    public String goIndex() {
        return "index";
    }
    
    /**
     * User zone index.
     */
    @RequestMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    /**
     * Administration zone index.
     */
    @RequestMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    /**
     * Shared zone index.
     */
    @RequestMapping("/shared/index")
    public String sharedIndex() {
        return "shared/index";
    }

    /**
     * Login form with error.
     */
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /**
     * Simulation of an exception.
     */
    @RequestMapping("/simulateError")
    public void simulateError() {
        throw new RuntimeException("This is a simulated error message");
    }

    /**
     * Error page.
     */
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

    /**
     * Error page.
     */
    @RequestMapping("/403.html")
    public String forbidden() {
        return "403";
    }

    /*
    @GetMapping(value = "/")
    public String getIndex(@ModelAttribute Famille famille) {
        return "login";
    }

    
    @PostMapping(value = "/index")
    public String postIndex(@ModelAttribute Famille famille) {
        return "index";
    }
     */
 /*
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView login(@ModelAttribute Famille famille,
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("index");

        return model;

    }
     */
// Login form
    /*
    @PostMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/hello")
    public String helloLogin() {
        System.out.println("in here ....");
        return "hello";
    }

    @RequestMapping("/test")
    public String testLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("the logged user " + authentication.getName() + " has role ROLE_ADMIN");
        System.out.println("test is here ....");
        return "test";
    }
     */
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

    @GetMapping(path = "/arts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ArticleInfo> goToIndex() {

        List<ArticleInfo> arts = this.articleFrnsService.findAllValid();

        return arts;
    }

    /*
	@GetMapping(path = "/invs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<InventaireArticle> listInventaires() {
		
		List<InventaireArticle> arts = this.inventaireArticleService.findInventaireArticle(artRef);
		
		return arts;
	}
     */
    @GetMapping("/invs_jasper/{artId}")
    public ModelAndView getInvsArticleReport(@PathVariable Long artId) {

        ArticleInfo art = this.ArticleInfoService.findArticleInfoById(artId);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        List<InventaireArticle> arts = this.inventaireArticleService.findInventaireArticle(artId);
        Collections.sort(arts);
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", JRdataSource);

        log.info("Print ArticleInfo id : " + art.getReference());
        log.info("Print ArticleInfo Puaht: " + art.getPuaht());
        log.info("Print ArticleInfo Puvht : " + art.getPuvht());

        parameterMap.put("reference", art.getReference());
        parameterMap.put("designation", art.getDesignation());
        parameterMap.put("quantite", art.getQuantite());
        parameterMap.put("puaht", art.getPuaht());
        parameterMap.put("puvht", art.getPuvht());

        /*
		parameterMap.put("myarticle", new ArticleInfo(1L,"balha01","balha",3,4,5));
		parameterMap.put("testparam", 52648.235);
         */
        return new ModelAndView("inventaireReport", parameterMap);

    }
    
   
}
