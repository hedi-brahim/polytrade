package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.db.Client;
import java.util.List;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findById(Long cltId);

    @Query("select sum(m.puaht*m.quantite*(1-m.remise/100)*(1+m.tva/100)) from Mouvement m join m.blVente b join b.client c "
            + "where b.factVente is null and m.sr = 0 and b.sr = 0 and c.id = :id")
    Double getBlTotalVenteByClient(@Param("id") Long cltId);

    @Query("select sum(m.puaht*m.quantite*(1-m.remise/100)*(1+m.tva/100)) from Mouvement m join m.factVente f join f.client c "
            + "where m.sr = 0 and f.sr = 0 and c.id = :id")
    Double getFactTotalVenteByClient(@Param("id") Long cltId);
    
    @Query("select sum(r.montant) from Reglement r join r.blVente b join b.client c "
            + "where b.factVente is null and r.sr = 0 and b.sr = 0 and c.id = :id")
    Double getBlTotalReglementByClient(@Param("id") Long cltId);

    @Query("select sum(r.montant) from Reglement r join r.factVente f join f.client c "
            + "where r.sr = 0 and f.sr = 0 and c.id = :id")
    Double getFactTotalReglementByClient(@Param("id") Long cltId);
    
    @Query("select c from Client c where c.sr = 0 order by c.raison")
    List<Client> listClients();

}
