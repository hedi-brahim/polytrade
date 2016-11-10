package com.polymec.web;

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

import com.polymec.model.Famille;
import com.polymec.service.FamilleService;
import com.polymec.model.ArticleInfo;
import com.polymec.service.ArticleInfoService;
import com.polymec.model.ArticleFrns;
import com.polymec.service.ArticleFrnsService;
import com.polymec.model.InventaireArticle;
import com.polymec.service.InventaireArticleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Controller for all examples contained in the sample.
 *
 * @author Thibault Duchateau
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class IndexController {

    private Logger logger = LoggerFactory.getLogger("com.polymec.web.MainController");
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

    /*
	@ModelAttribute("allArticleFrns")
	@ResponseBody
    public List<ArticleFrns> populateArticleFrns() {
        return this.articleFrnsService.findAllValid();
    }	
     */
    @PostMapping("/familleReport")
    public ModelAndView getArticlesReport(@RequestParam("id") Long id) {

        logger.info("Print Famille id : " + id);

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

    @RequestMapping(value = "/")
    public String goToIndex(@ModelAttribute Famille famille) {
        return "index";
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

        logger.info("Print ArticleInfo id : " + art.getReference());
        logger.info("Print ArticleInfo Puaht: " + art.getPuaht());
        logger.info("Print ArticleInfo Puvht : " + art.getPuvht());

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
