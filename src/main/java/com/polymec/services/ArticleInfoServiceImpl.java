package com.polymec.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.polymec.domain.ArticleInfo;
import com.polymec.dao.ArticleInfoRepository;
import java.util.ArrayList;
import java.util.List;

@Service("jpaArticleInfoService")
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoRepository articleInfoRepository;

    @Override
    public List<ArticleInfo> listArticlesExistants() {
        return (new ArrayList<ArticleInfo>(articleInfoRepository.listArticlesExistants()));
    }
    
    @Override
    public List<ArticleInfo> listArticles() {
        return (new ArrayList<ArticleInfo>(articleInfoRepository.listArticles()));
    }
    
    @Override
    public ArticleInfo findArticleInfoById(Long artId) {
        return articleInfoRepository.findById(artId);
    }
    
    @Override
    public List<ArticleInfo> findByFamille(Long id) {
        return (new ArrayList<ArticleInfo>(articleInfoRepository.findByFamille(id)));
    }    
}
