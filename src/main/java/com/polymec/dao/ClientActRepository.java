package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.ClientAct;
import org.springframework.data.repository.query.Param;

public interface ClientActRepository extends JpaRepository<ClientAct, Long> {
    /*
    @Query("select new  ClientAct(c.date, c.date, 'BL', c.numero, '', 0.0, 0.0, 0.0, 0.0, 0.0, sum(mvts.puaht*mvts.quantite*(1-mvts.remise/100)*(1+mvts.tva/100)), sum(regls.montant)) from Reglement regls "
            + " right join regls.blVente c join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.factVente is null and mvts.sr = 0 and c.sr = 0 and clt.id = ?1 "
            + " group by c.numero "
            + " order by c.date desc")
    List<ClientAct> listBlVentes(Long cltId);
    
    @Query("select new ClientAct(c.date, c.date, 'FACTURE', c.numero, '', 0.0, 0.0, 0.0, 0.0, 0.0, sum(mvts.puaht*mvts.quantite*(1-mvts.remise/100)*(1+mvts.tva/100)), sum(regls.montant)) from Reglement regls "
            + " right join regls.factVente c join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "          
            + " where c.sr = 0 and mvts.sr = 0 and clt.id = ?1 "
            + " group by c.numero "              
            + " order by c.date desc")
    List<ClientAct> listFactureVentes(Long cltId);
    
    @Query("select new  ClientAct(c.date, c.date, 'BL', c.numero, art.designation, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva, 0.0, 0.0) from BlVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.factVente is null and mvts.sr = 0 and c.sr = 0 and clt.id = ?1 "
            + " order by c.date desc")
    List<ClientAct> listArticlesBlVentes(Long cltId);

    @Query("select new ClientAct(c.date, c.date, 'FACTURE', c.numero, art.designation, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva, 0.0, 0.0) from FactVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "          
            + " where c.sr = 0 and mvts.sr = 0 and clt.id = ?1 "             
            + " order by c.date desc")
    List<ClientAct> listArticlesFactureVentes(Long cltId);
*/
//@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?0", nativeQuery = true)
    
    
    /*
   @Query("select new ClientAct(c.date, c.date, 'BL', c.numero, art.designation, mvts.quantite, "
	            + " art.puaht, mvts.puaht, mvts.remise, mvts.tva, c.mntTot, 0.0)  "
	            + " from BlVente c "
		            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
		            + " where c.factVente is null and mvts.sr = 0 and c.sr = 0 and clt.id = :id "
		            + " and c.mntTot = (select sum(m.puaht*m.quantite*(1-m.remise/100)*(1+m.tva/100)) from Mouvement m "
						            + " where m.sr = 0 and m.blVente.id = c.id) "
	            + " order by c.date desc")
            */
    
    List<ClientAct> listArticlesBlVentes(@Param("cltId") Long cltId);
    
    List<ClientAct> listArticlesFactVentes(@Param("cltId") Long cltId);  
    
    List<ClientAct> listArticlesEncoursBlVentes(@Param("cltId") Long cltId);
    
    List<ClientAct> listArticlesEncoursFactVentes(@Param("cltId") Long cltId);    
}
