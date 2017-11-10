package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.Docs;
import java.awt.Image;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface DocsRepository extends JpaRepository<Docs, Long> {

    Docs findById(Long docId);
    
    @Query("select c from Docs c where c.sr = 0 order by c.nom")
    List<Docs> listDocs();

}
