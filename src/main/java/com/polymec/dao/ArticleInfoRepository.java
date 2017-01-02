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
    
    //@Query("select c from ArticleFrns c where c.sr = 0 and c.quantite > 0 and c.article.famille.id = ?1 order by c.article.reference, c.article.designation")
            
    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
        + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht, c.tva) "
        + "from Article c left join c.famille f where c.articleFrns.sr = 0 "
        + "and c.famille.id = ?1 and c.articleFrns.quantite > 0 order by c.reference, c.designation")
    List<ArticleInfo> findByFamille(Long id);    
        
    //c.famille.designation
}
