package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.Client;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findById(Long cltId);
    
    @Query("select c from Client c where c.sr = 0 order by c.raison")
    List<Client> listClients();

}
