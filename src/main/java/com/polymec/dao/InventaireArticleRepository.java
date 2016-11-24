package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.InventaireArticle;

public interface InventaireArticleRepository extends JpaRepository<InventaireArticle, Long> {
    //private String op_achat = "Achat";

    /*
	@Query("select distinct c from Famille c "
			+ " join c.articles art join art.articleFrns artFrs "
			+ " where c.articles is not empty and artFrs.quantite != 0 and artFrs.sr = 0 "
			+ " order by c.designation asc")
     */
    @Query("select new InventaireArticle(c.date, 'Achat', c.numero, f.raison, mvts.quantite, mvts.puaht, mvts.remise, art.designation) from BlAchat c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join af.fournisseur f "
            + " where af.sr = 0 and art.id = ?1 order by c.date desc")
    List<InventaireArticle> findAchatArticle(Long artId);

    @Query("select new InventaireArticle(c.date, 'Vente', c.numero, clt.raison, mvts.quantite, mvts.puaht, mvts.remise, art.designation) from BlVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where af.sr = 0 and art.id = ?1 order by c.date desc")
    List<InventaireArticle> findVenteArticle(Long artId);

    @Query("select new InventaireArticle(c.date, 'Inventaire', '', '', c.quantite, 0.0, 0.0, art.designation) from Inventaire c "
            + " join c.articleFrns af join af.article art "
            + " where af.sr = 0 and art.id = ?1 order by c.date desc")
    List<InventaireArticle> findInventaireArticle(Long artId);

}
