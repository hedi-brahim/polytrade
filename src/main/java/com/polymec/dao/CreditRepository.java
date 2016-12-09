package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.Credit;

public interface CreditRepository extends JpaRepository<Credit, Long> {


    @Query("select new Credit(mvts.date, mvts.dateModif, 'BL', c.numero, clt.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, art.designation) from BlVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.factVente is null and c.sr = 0 order by c.date desc")
    List<Credit> listBl();
    
    @Query("select new Credit(mvts.date, mvts.dateModif, 'Facture', c.numero, clt.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, art.designation) from FactVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.sr = 0 and mvts.sr = 0 order by c.date desc")
    List<Credit> listFact();
    
    @Query("select new Credit(c.date, c.dateModif, 'Reglement', c.numero, clt.raison, 1.0, 0.0, c.montant/1.18, 0.0, ' ') from Reglement c "
            + " join c.blVente blv join blv.client clt "
            + " where c.blVente.sr = 0 and c.sr = 0 order by c.date desc")
    List<Credit> listBlReglements(); 
    
    @Query("select new Credit(c.date, c.dateModif, 'Reglement', c.numero, clt.raison, 1.0, 0.0, c.montant/1.18, 0.0, ' ') from Reglement c "
            + " join c.factVente fc join fc.client clt "
            + " where c.factVente.sr = 0 and c.sr = 0 order by c.date desc")
    List<Credit> listFactReglements();
    
    @Query("select new Credit(mvts.date, mvts.dateModif, 'BLA', c.numero, frs.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, art.designation) from BlAchat c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.fournisseur frs "
            + " where c.factAchat is null and c.sr = 0 order by c.date desc")
    List<Credit> listBlAchats();
    
    @Query("select new Credit(mvts.date, mvts.dateModif, 'FACTA', c.numero, frs.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, art.designation) from FactAchat c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.fournisseur frs "
            + " where c.sr = 0 and mvts.sr = 0 order by c.date desc")
    List<Credit> listFactAchats();
    
    @Query("select new Credit(c.date, c.dateModif, 'Reglement', c.numero, frs.raison, 1.0, 0.0, c.montant/1.18, 0.0, ' ') from Reglement c "
            + " join c.blAchat blv join blv.fournisseur frs "
            + " where c.blAchat.sr = 0 and c.sr = 0 order by c.date desc")
    List<Credit> listBlAReglements(); 
    
    @Query("select new Credit(c.date, c.dateModif, 'Reglement', c.numero, frs.raison, 1.0, 0.0, c.montant/1.18, 0.0, ' ') from Reglement c "
            + " join c.factAchat fc join fc.fournisseur frs "
            + " where c.factAchat.sr = 0 and c.sr = 0 order by c.date desc")
    List<Credit> listFactAReglements();    
}
