/*
 * Copyright 2016 Hedi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.polymec.services;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

import com.polymec.dao.ClientActRepository;
<<<<<<< HEAD
=======
import com.polymec.dao.ClientRepository;
>>>>>>> develop
import com.polymec.domain.ClientAct;

@Service("jpaClientActService")
public class ClientActServiceImpl implements ClientActService {

    @Autowired
    private ClientActRepository clientActRepository;

<<<<<<< HEAD
    @Override
    public List<ClientAct> listClientActs(Long cltId) {
        List<ClientAct> acts = new ArrayList<ClientAct>(clientActRepository.listArticlesBlVentes(cltId)); 
        acts.addAll(new ArrayList<ClientAct>(clientActRepository.listArticlesFactVentes(cltId)));                
        return (acts);
    }
=======
    @Autowired
    private ClientRepository clientRepository;
    
    @Override
    public List<ClientAct> listClientActs(Long cltId) {
        List<ClientAct> acts = new ArrayList<ClientAct>(clientActRepository.listArticlesBlVentes(cltId)); 
        acts.addAll(new ArrayList<ClientAct>(clientActRepository.listArticlesFactVentes(cltId)));   
        //List<ClientAct> acts = new ArrayList<ClientAct>(clientActRepository.listArticlesFactVentes(cltId));         
        return (acts);
    }
    
    
    @Override
    public Double getMntTotVentes(Long cltId) {
        return (clientRepository.getBlTotalVenteByClient(cltId) + clientRepository.getFactTotalVenteByClient(cltId)); 
    }
    

    @Override
    public Double getMntTotReglements(Long cltId) {
        return (clientRepository.getBlTotalReglementByClient(cltId) + clientRepository.getFactTotalReglementByClient(cltId)); 
    }    
>>>>>>> develop
}

