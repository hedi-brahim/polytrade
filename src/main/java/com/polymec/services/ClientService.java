package com.polymec.services;

import java.util.List;

<<<<<<< HEAD
import com.polymec.domain.Client;
=======
import com.polymec.domain.db.Client;
>>>>>>> develop

public interface ClientService {

    Client findById(Long cltId);
    List<Client> listClients();
}
