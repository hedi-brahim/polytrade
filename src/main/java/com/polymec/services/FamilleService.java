package com.polymec.services;

import java.util.List;

import com.polymec.domain.Famille;

public interface FamilleService {

    List<Famille> findAllValid();

    Famille findById(Long id);
    
    Famille save(Famille fml);
}
