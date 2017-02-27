package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import com.polymec.domain.Fournisseur;
=======
import com.polymec.domain.db.Fournisseur;
>>>>>>> develop
import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("select c from Fournisseur c where c.sr = 0 order by c.raison")
    List<Fournisseur> listFournisseurs();

}
