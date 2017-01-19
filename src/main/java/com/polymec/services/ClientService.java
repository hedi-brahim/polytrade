package com.polymec.services;

import java.util.List;

import com.polymec.domain.db.Client;

public interface ClientService {

    Client findById(Long cltId);
    List<Client> listClients();
}
