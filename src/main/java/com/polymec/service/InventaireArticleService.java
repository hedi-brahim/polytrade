package com.polymec.service;

import java.util.List;

import com.polymec.domain.InventaireArticle;

public interface InventaireArticleService {

    List<InventaireArticle> findInventaireArticle(Long artId);
}
