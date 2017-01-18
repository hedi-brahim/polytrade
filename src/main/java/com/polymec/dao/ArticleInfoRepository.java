package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.ArticleInfo;
import java.util.List;

public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Long> {

    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
            + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht, c.tva) "
            + "from Article c left join c.famille f where c.articleFrns.sr = 0 and c.articleFrns.quantite > 0 order by c.reference, c.designation")
    List<ArticleInfo> listArticlesExistants();
    
    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
            + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht, c.tva) "
            + "from Article c left join c.famille f where c.articleFrns.sr = 0 order by c.reference, c.designation")
    List<ArticleInfo> listArticles();
    
    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
            + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht, c.tva) "
            + "from Article c left join c.famille f where c.id = ?1")
    ArticleInfo findById(Long id);
    
    //Inventaire d'articles par famille avec date du dernier inventaire        
    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
        + "c.designation, inv.quantite, inv.date, fr.quantite, c.puaht, c.puvht, c.tva) "
        + "from Article c left join c.famille f join c.articleFrns fr left join fr.inventaires inv where fr.sr = 0 "
        + "and c.famille.id = ?1 and fr.quantite > 0 "
        + "and (inv.date = (select max(inv1.date) from Inventaire inv1 where inv1.articleFrns.id = fr.id) or inv.date is null)"
        + "order by c.reference, c.designation")
    List<ArticleInfo> findByFamille(Long id);    
        
    //c.famille.designation
}
