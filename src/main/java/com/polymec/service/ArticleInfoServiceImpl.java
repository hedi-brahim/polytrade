package com.polymec.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.polymec.domain.ArticleInfo;
import com.polymec.dao.ArticleInfoRepository;

@Service("jpaArticleInfoService")
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoRepository ArticleInfoRepository;

    @Override
    public ArticleInfo findArticleInfoById(Long artId) {
        return ArticleInfoRepository.findById(artId);
    }
}
