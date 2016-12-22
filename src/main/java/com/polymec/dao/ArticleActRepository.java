package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.ArticleAct;

public interface ArticleActRepository extends JpaRepository<ArticleAct, Long> {

    @Query("select new ArticleAct(c.date, c.date, 'BLA', c.numero, f.raison, mvts.quantite, 0.0, mvts.puaht, mvts.remise, mvts.tva) from BlAchat c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join af.fournisseur f "
            + " where c.factAchat is null and mvts.sr = 0 and c.sr = 0 and art.id = ?1 order by c.date desc")
    List<ArticleAct> listBlAchats(Long artId);

    @Query("select new ArticleAct(c.date, c.date, 'FACTA', c.numero, frs.raison, mvts.quantite, 0.0, mvts.puaht, mvts.remise, mvts.tva) from FactAchat c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.fournisseur frs "
            + " where c.sr = 0 and mvts.sr = 0 and art.id = ?1 order by c.date desc")
    List<ArticleAct> listFactureAchats(Long artId);

    @Query("select new ArticleAct(c.date, c.date, 'BLV', c.numero, clt.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva) from BlVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.factVente is null and mvts.sr = 0 and c.sr = 0 and art.id = ?1 order by c.date desc")
    List<ArticleAct> listBlVentes(Long artId);

    @Query("select new ArticleAct(c.date, c.date, 'FACTV', c.numero, clt.raison, mvts.quantite, art.puaht, mvts.puaht, mvts.remise, mvts.tva) from FactVente c "
            + " join c.mvts mvts join mvts.articleFrns af join af.article art join c.client clt "
            + " where c.sr = 0 and mvts.sr = 0 and art.id = ?1 order by c.date desc")
    List<ArticleAct> listFactureVentes(Long artId);

    @Query("select new ArticleAct(c.date, c.date, 'Inventaire', '', '', c.quantite, 0.0, 0.0, 0.0, 0.0) from Inventaire c "
            + " join c.articleFrns af join af.article art "
            + " where af.sr = 0 and art.id = ?1 order by c.date desc")
    List<ArticleAct> listInventaires(Long artId);

}
