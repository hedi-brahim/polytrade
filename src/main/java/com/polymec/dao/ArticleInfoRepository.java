package com.polymec.dao;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.ArticleInfo;

public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Long> {

    @Query("select new ArticleInfo(c.id, f.designation, c.reference, "
            + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht, c.tva) "
            + "from Article c left join c.famille f where c.id = ?1")
    ArticleInfo findById(Long id);
    
    //c.famille.designation
}
