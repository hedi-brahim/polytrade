package com.polymec.service;

import java.util.List;

import com.polymec.model.ArticleFrns;
import com.polymec.model.ArticleInfo;

public interface ArticleFrnsService {

    List<ArticleInfo> findAllValid();

    List<ArticleFrns> findByFamille(Long id);
    //List<Article> findByReference(String reference);
    //List<Article> findAllWithDetail();	
    //Page<Article> getArticles(Integer pageNumber);
    //List<Article> findByFirstNameAndLastName(String firstName, String lastName)	;

    ArticleFrns findById(Long id);

    ArticleFrns save(ArticleFrns articleFrns);

    void delete(ArticleFrns articleFrns);
}
