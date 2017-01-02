/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.controller;

import com.polymec.domain.User;
import com.polymec.dao.UserRepository;
import com.polymec.domain.ArticleAct;
import com.polymec.domain.ArticleInfo;
import com.polymec.domain.Credit;
import com.polymec.domain.Famille;
import com.polymec.domain.UserRole;
import com.polymec.service.ArticleInfoService;
import com.polymec.service.ArticleActService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.polymec.service.CreditService;
import com.polymec.service.FamilleService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hedi
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger log = LoggerFactory.getLogger("com.polymec.controller.AdminController");
    
    @Autowired
    private CreditService reglementService;

    @Autowired
    private ArticleInfoService articleInfoService;
    
    @Autowired
    private FamilleService familleService;


    @Autowired
    private ArticleActService articleActService;



    @Autowired
    UserRepository userRepository;

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
    
    /**
     * Registration page.
     */
    @GetMapping("/index")
    public String index(@ModelAttribute Famille famille) {
        return "admin/index";
    }

    /**
     * Recettes page.
     */
    @GetMapping("/recettes")
    public String pageRecettes() {
        return "admin/recettes";
    }

    /**
     * Depenses page.
     */
    @GetMapping("/depenses")
    public String pageDepenses() {
        return "admin/depenses";
    }

    /**
     * Registration page.
     */
    @GetMapping("/register")
    public String register(@ModelAttribute User user) {
        return "admin/registration";
    }

    @PostMapping("/register")
    public String saveNewUser(@ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/registration";
        }

        for (UserRole role : user.getRoles()) {
            role.setUser(user);
        }

        userRepository.save(user);
        return "admin/complete";
    }

 
   
    @GetMapping(path = "/reglements", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Credit> getReglements() {

        List<Credit> regs = this.reglementService.listReglements();

        return regs;
    }

    @GetMapping(path = "/json_recettes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Credit> getRecettes() {

        List<Credit> regs = this.reglementService.listReglements();

        return regs;
    }

    @GetMapping(path = "/json_depenses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Credit> getDepenses() {

        List<Credit> regs = this.reglementService.listAchatsReglements();

        return regs;
    }
/*
    @GetMapping("/invs_jasper/{artId}")
    public ModelAndView getInvsArticleReport(@PathVariable Long artId) {

        ArticleInfo art = this.ArticleInfoService.findArticleInfoById(artId);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        List<InventaireArticle> arts = this.inventaireArticleService.findInventaireArticle(artId);
        Collections.sort(arts);
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", JRdataSource);

        parameterMap.put("reference", art.getReference());
        parameterMap.put("designation", art.getDesignation());
        parameterMap.put("quantite", art.getQuantite());
        parameterMap.put("puaht", art.getPuaht());
        parameterMap.put("puvht", art.getPuvht());

        return new ModelAndView("inventaireReport", parameterMap);

    }
*/
}
