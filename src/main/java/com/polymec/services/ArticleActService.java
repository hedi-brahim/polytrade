package com.polymec.services;

import java.util.List;

import com.polymec.domain.ArticleAct;

public interface ArticleActService {

    List<ArticleAct> listArticleActs(Long artId);
}
