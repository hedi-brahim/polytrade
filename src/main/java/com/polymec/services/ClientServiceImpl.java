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

import com.polymec.dao.ClientRepository;
import com.polymec.domain.db.Client;

@Service("jpaClientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client findById(Long cltId)
    {
        return clientRepository.findById(cltId);
    }
    
    @Override
    public List<Client> listClients() {
        List<Client> clts = new ArrayList<Client>(clientRepository.listClients()); 
        return (clts);
    }
}

