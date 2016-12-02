/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.controller;

import com.polymec.dao.RoleRepository;
import com.polymec.domain.User;
import com.polymec.dao.UserRepository;
import com.polymec.domain.Credit;
import com.polymec.domain.Role;
import com.polymec.domain.UserRole;
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
    UserRepository userRepository;
    
    @ModelAttribute("allRoles")
    public List<Role> populateFeatures() {
        return Arrays.asList(Role.ALL);
    }

    /**
     * Registration page.
     */
    @GetMapping("/index")
    public String index() {
        return "admin/index";
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
}
