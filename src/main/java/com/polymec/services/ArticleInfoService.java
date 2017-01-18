package com.polymec.services;

import com.polymec.domain.ArticleInfo;
import java.util.List;

public interface ArticleInfoService {
    
    List<ArticleInfo> listArticlesExistants(); 
    List<ArticleInfo> listArticles();      
    ArticleInfo findArticleInfoById(Long artId);
    List<ArticleInfo> findByFamille(Long id);    
    
}
