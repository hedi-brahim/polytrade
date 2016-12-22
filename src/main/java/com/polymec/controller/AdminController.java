/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.controller;

import com.polymec.dao.RoleRepository;
import com.polymec.domain.User;
import com.polymec.dao.UserRepository;
import com.polymec.domain.ArticleAct;
import com.polymec.domain.ArticleInfo;
import com.polymec.domain.Credit;
import com.polymec.domain.Famille;
import com.polymec.domain.InventaireArticle;
import com.polymec.domain.Role;
import com.polymec.domain.UserRole;
import com.polymec.service.ArticleInfoService;
import com.polymec.service.ArticleActService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.polymec.service.CreditService;
import com.polymec.service.FamilleService;
import com.polymec.service.InventaireArticleService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hedi
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CreditService reglementService;

    @Autowired
    private FamilleService familleService;
    
    @Autowired
    private InventaireArticleService inventaireArticleService;
  
    @Autowired
    private ArticleActService articleActService;
    
    @Autowired
    private ArticleInfoService ArticleInfoService;
    
    @Autowired
    UserRepository userRepository;
    
    
    @ModelAttribute("allFamille")
    public List<Famille> populateallFamille() {

        Famille fml = new Famille("STOCK TOTAL");
        List<Famille> fmls = new ArrayList<Famille>();
        fmls.add(fml);
        fmls.addAll(this.familleService.findAllValid());

        return fmls;
    }
    
    @ModelAttribute("allRoles")
    public List<Role> populateFeatures() {
        return Arrays.asList(Role.ALL);
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
    public String saveNewUser(@ModelAttribute User user,BindingResult result)
    {
        if(result.hasErrors())
        {
            return "admin/registration";
        }
        
        for(UserRole role: user.getRoles())
        {
            role.setUser(user);
        }
        
        userRepository.save(user);
        return "admin/complete";
        //return "complete";

    }

    /*
    @GetMapping("/reglements")
    public ModelAndView getReglementReport() {

        //ArticleInfo art = this.ArticleInfoService.findArticleInfoById(artId);

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

        return new ModelAndView("inventaireReport", parameterMap);

    }  
    
    */
    
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

        @GetMapping("/invs_jasper/{artId}")
    public ModelAndView getInvsArticleReport(@PathVariable Long artId) {

        ArticleInfo art = this.ArticleInfoService.findArticleInfoById(artId);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        List<InventaireArticle> arts = this.inventaireArticleService.findInventaireArticle(artId);
        Collections.sort(arts);
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", JRdataSource);
/*
        log.info("Print ArticleInfo id : " + art.getReference());
        log.info("Print ArticleInfo Puaht: " + art.getPuaht());
        log.info("Print ArticleInfo Puvht : " + art.getPuvht());
*/
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
    
        @GetMapping("/art_acts/{artId}")
    public ModelAndView listArticleActsReport(@PathVariable Long artId) {

        ArticleInfo art = this.ArticleInfoService.findArticleInfoById(artId);

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        List<ArticleAct> arts = this.articleActService.listArticleActs(artId);
        Collections.sort(arts);
        JRDataSource JRdataSource = new JRBeanCollectionDataSource(arts);
        parameterMap.put("datasource", JRdataSource);
/*
        log.info("Print ArticleInfo id : " + art.getReference());
        log.info("Print ArticleInfo Puaht: " + art.getPuaht());
        log.info("Print ArticleInfo Puvht : " + art.getPuvht());
*/
        parameterMap.put("reference", art.getReference());
        parameterMap.put("designation", art.getDesignation());
        parameterMap.put("quantite", art.getQuantite());
        parameterMap.put("puaht", art.getPuaht());
        parameterMap.put("puvht", art.getPuvht());

        /*
		parameterMap.put("myarticle", new ArticleInfo(1L,"balha01","balha",3,4,5));
		parameterMap.put("testparam", 52648.235);
         */
        return new ModelAndView("articleActsReport", parameterMap);

    }    
    
}
