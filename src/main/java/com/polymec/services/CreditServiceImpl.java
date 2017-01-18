package com.polymec.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.domain.Credit;
import com.polymec.dao.CreditRepository;

@Service("jpaReglementService")
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository ReglementRepository;

    @Override
    public List<Credit> listReglements() {
        List<Credit> crds = new ArrayList<Credit>(ReglementRepository.listBl());
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listFact()));
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listBlReglements()));  
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listFactReglements()));          
        return (crds);
    }
    
    @Override
    public List<Credit> listAchatsReglements() {
        List<Credit> crds = new ArrayList<Credit>(ReglementRepository.listBlAchats());
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listFactAchats()));
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listBlAReglements()));  
        crds.addAll(new ArrayList<Credit>(ReglementRepository.listFactAReglements()));          
        return (crds);
    }    
}
