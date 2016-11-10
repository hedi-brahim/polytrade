package com.polymec.repository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.model.ArticleInfo;

public interface ArticleInfoRepository extends JpaRepository<ArticleInfo, Long> {

    @Query("select new ArticleInfo(c.id, c.famille.designation, c.reference, "
            + "c.designation, c.articleFrns.quantite, c.puaht, c.puvht) "
            + "from Article c where c.id = ?1")
    ArticleInfo findById(Long id);
}
