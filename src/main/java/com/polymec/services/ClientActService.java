package com.polymec.services;

import java.util.List;

import com.polymec.domain.ClientAct;

public interface ClientActService {

    List<ClientAct> listClientActs(Long cltId);    
    List<ClientAct> listClientEncoursActs(Long cltId);
    Double getMntTotVentes(Long cltId);
    Double getMntTotReglements(Long cltId);
    
}
