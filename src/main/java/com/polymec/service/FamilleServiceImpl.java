package com.polymec.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.domain.Famille;
import com.polymec.dao.FamilleRepository;

@Service("jpaFamilleService")
public class FamilleServiceImpl implements FamilleService {

    @Autowired
    private FamilleRepository familleRepository;

    //private Log log = LogFactory.getLog(FamilleServiceImpl.class);
    @Override
    public List<Famille> findAllValid() {
        return (new ArrayList<Famille>(familleRepository.findAllValid()));
    }

    @Override
    public Famille findById(Long id) {
        return familleRepository.findOne(id);
    }

}
