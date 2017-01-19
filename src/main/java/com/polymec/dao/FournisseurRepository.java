package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.db.Fournisseur;
import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("select c from Fournisseur c where c.sr = 0 order by c.raison")
    List<Fournisseur> listFournisseurs();

}
