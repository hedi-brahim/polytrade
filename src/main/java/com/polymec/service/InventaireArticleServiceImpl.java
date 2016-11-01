package com.polymec.service;

import java.text.ParseException;

import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
//import com.google.common.collect.Lists;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.polymec.model.InventaireArticle;
import com.polymec.repository.InventaireArticleRepository;


@Service("jpaInventaireArticleService")
public class InventaireArticleServiceImpl implements InventaireArticleService {
 
	@Autowired
	private InventaireArticleRepository inventaireArticleRepository;
	
    @Override
    public List<InventaireArticle> findInventaireArticle() {
        return (new ArrayList<InventaireArticle>(inventaireArticleRepository.findInventaireArticle()));
    }	

}
