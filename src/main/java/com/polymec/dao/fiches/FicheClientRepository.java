package com.polymec.dao.fiches;

import com.polymec.dao.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.fiches.FicheClient;
import org.springframework.data.repository.query.Param;

public interface FicheClientRepository extends JpaRepository<FicheClient, Long> {
     
}
