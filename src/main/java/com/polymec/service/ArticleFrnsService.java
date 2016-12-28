package com.polymec.service;

import java.util.List;

import com.polymec.domain.ArticleFrns;
import com.polymec.domain.ArticleInfo;

public interface ArticleFrnsService {

    List<ArticleInfo> findAllValid();

    List<ArticleFrns> findByFamille(Long id);
    List<ArticleInfo> listArticles();    
    //List<Article> findByReference(String reference);
    //List<Article> findAllWithDetail();	
    //Page<Article> getArticles(Integer pageNumber);
    //List<Article> findByFirstNameAndLastName(String firstName, String lastName)	;

    ArticleFrns findById(Long id);

    ArticleFrns save(ArticleFrns articleFrns);

    void delete(ArticleFrns articleFrns);
}
