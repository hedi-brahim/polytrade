/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polymec.controllers;

import com.polymec.domain.ArticleAct;
import com.polymec.domain.ArticleInfo;
import com.polymec.domain.ClientAct;
import com.polymec.domain.Credit;
import com.polymec.domain.Client;
import com.polymec.domain.Docs;
import com.polymec.domain.Famille;
import com.polymec.domain.Fournisseur;
import com.polymec.services.ArticleActService;
import com.polymec.services.ArticleInfoService;
import com.polymec.services.ClientActService;
import com.polymec.services.ClientService;
import com.polymec.services.CreditService;
import com.polymec.services.FamilleService;
import com.polymec.services.FournisseurService;
import com.polymec.services.DocsService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Blob;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Hedi
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger("com.polymec.controllers.UserController");

    @Autowired
    private CreditService reglementService;

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

    @Autowired
    private DocsService docsService;

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
    /*
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
     */
    // Module liste des articles
    @GetMapping(path = "/list_articles", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    // Module liste des clients
    @GetMapping(path = "/list_clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    // Module Fiche Article
    /*
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
     */
    /**
     * Index page (Acceuil).
     */
    @GetMapping("/index")
    public String index(@ModelAttribute Famille famille) {
        return "pages/user/stock";
    }

    /**
     * Index page (Acceuil).
     */
    @GetMapping("/stock")
    public String stock(@ModelAttribute Famille famille) {
        return "pages/user/stock";
    }

    /**
     * Client page.
     */
    @GetMapping("/clients")
    public String clients() {
        return "pages/user/clients";
    }

    /**
     * Registration page.
     */
    /*
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

        Blob logo;
        byte[] imgData = null;
        BufferedImage bImg = null;

        // pass article infos to jasper reports
        Client clt = this.clientService.findById(cltId);
        Docs img = this.docsService.findById(2L);
        logo = img.getImg();
        try {

            imgData = logo.getBytes(1, (int) logo.length());
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        try {
            // convert byte array back to BufferedImage
            InputStream in = new ByteArrayInputStream(imgData);
            bImg = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        /*
        parameterMap.put("mntActs", this.clientActService.getMntTotVentes(cltId));
        parameterMap.put("mntRegs", this.clientActService.getMntTotReglements(cltId));
        */
        parameterMap.put("raison", clt.getRaison());
        parameterMap.put("tel", clt.getTel());
        parameterMap.put("gsm", clt.getGsm());
        parameterMap.put("fax", clt.getFax());
        parameterMap.put("logo", bImg);

        log.info("Print Client raison : " + clt.getRaison());

        // pass list of article acts to jasper reports
        List<ClientAct> acts = this.clientActService.listClientEncoursActs(cltId);
        Collections.sort(acts);
        JRDataSource jrDS = new JRBeanCollectionDataSource(acts);
        parameterMap.put("datasource", jrDS);

        return new ModelAndView("ficheClient", parameterMap);

    }
    
    // Module Fiche Client
    @GetMapping(value = {"fiche_client_all/{cltId}"})
    public ModelAndView ficheClientAll(@PathVariable Long cltId) {

        log.info("Print Client id : " + cltId);
        Map<String, Object> parameterMap = new HashMap<String, Object>();

        Blob logo;
        byte[] imgData = null;
        BufferedImage bImg = null;

        // pass article infos to jasper reports
        Client clt = this.clientService.findById(cltId);
        Docs img = this.docsService.findById(2L);
        logo = img.getImg();
        try {

            imgData = logo.getBytes(1, (int) logo.length());
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        try {
            // convert byte array back to BufferedImage
            InputStream in = new ByteArrayInputStream(imgData);
            bImg = ImageIO.read(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        /*
        parameterMap.put("mntActs", this.clientActService.getMntTotVentes(cltId));
        parameterMap.put("mntRegs", this.clientActService.getMntTotReglements(cltId));
*/
        parameterMap.put("raison", clt.getRaison());
        parameterMap.put("tel", clt.getTel());
        parameterMap.put("gsm", clt.getGsm());
        parameterMap.put("fax", clt.getFax());
        parameterMap.put("logo", bImg);

        log.info("Print Client raison : " + clt.getRaison());

        // pass list of article acts to jasper reports
        List<ClientAct> acts = this.clientActService.listClientActs(cltId);
        Collections.sort(acts);
        JRDataSource jrDS = new JRBeanCollectionDataSource(acts);
        parameterMap.put("datasource", jrDS);

        return new ModelAndView("ficheClient", parameterMap);

    }    
}
