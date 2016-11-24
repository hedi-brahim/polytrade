package com.polymec.service;

import java.util.List;

import com.polymec.domain.Article;

public interface ArticleService {

    List<Article> findAll();

    List<Article> findByReference(String reference);
    //List<Article> findAllWithDetail();	
    //Page<Article> getArticles(Integer pageNumber);
    //List<Article> findByFirstNameAndLastName(String firstName, String lastName)	;

    Article findById(Long id);

    Article save(Article article);

    void delete(Article article);
}
