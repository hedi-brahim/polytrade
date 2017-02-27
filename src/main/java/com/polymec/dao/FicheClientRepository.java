package com.polymec.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.FicheClient;

public interface FicheClientRepository extends JpaRepository<FicheClient, Long> {
     
}
