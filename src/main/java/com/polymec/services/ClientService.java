package com.polymec.services;

import java.util.List;

import com.polymec.domain.Client;

public interface ClientService {

    Client findById(Long cltId);
    List<Client> listClients();
}
