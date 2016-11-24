package com.polymec.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.domain.InventaireArticle;
import com.polymec.dao.InventaireArticleRepository;

@Service("jpaInventaireArticleService")
public class InventaireArticleServiceImpl implements InventaireArticleService {

    @Autowired
    private InventaireArticleRepository inventaireArticleRepository;

    @Override
    public List<InventaireArticle> findInventaireArticle(Long artId) {
        List<InventaireArticle> arts = new ArrayList<InventaireArticle>(inventaireArticleRepository.findAchatArticle(artId));
        arts.addAll(new ArrayList<InventaireArticle>(inventaireArticleRepository.findVenteArticle(artId)));
        arts.addAll(new ArrayList<InventaireArticle>(inventaireArticleRepository.findInventaireArticle(artId)));
        return (arts);
    }
}
