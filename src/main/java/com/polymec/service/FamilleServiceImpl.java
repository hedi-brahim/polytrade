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
import com.google.common.collect.Lists;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.polymec.model.Famille;
import com.polymec.repository.FamilleRepository;


@Service("jpaFamilleService")
public class FamilleServiceImpl implements FamilleService {
 
	@Autowired
	private FamilleRepository familleRepository;

    private Log log = LogFactory.getLog(FamilleServiceImpl.class);
	
    @Override
    public List<Famille> findAllValid() {
        return Lists.newArrayList(familleRepository.findAllValid());
    }
	
    @Override
    public Famille findById(Long id) {
        return familleRepository.findOne(id);
    }	

}
