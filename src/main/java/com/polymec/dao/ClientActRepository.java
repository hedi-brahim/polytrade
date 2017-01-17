package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.ClientAct;

public interface ClientActRepository extends JpaRepository<ClientAct, Long> {

    @Query("select new  ClientAct(c.date, c.date, 'BL', c.numero, art.designation, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva) from BlVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.factVente is null and mvts.sr = 0 and c.sr = 0 and clt.id = ?1 order by c.date desc")
    List<ClientAct> listBlVentes(Long cltId);

    @Query("select new ClientAct(c.date, c.date, 'FACTURE', c.numero, art.designation, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva) from FactVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.sr = 0 and mvts.sr = 0 and clt.id = ?1 order by c.date desc")
    List<ClientAct> listFactureVentes(Long cltId);

}
