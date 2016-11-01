package com.polymec.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polymec.model.InventaireArticle;

public interface InventaireArticleRepository extends JpaRepository<InventaireArticle, Long> {
	
/*
	@Query("select distinct c from Famille c "
			+ " join c.articles art join art.articleFrns artFrs "
			+ " where c.articles is not empty and artFrs.quantite != 0 and artFrs.sr = 0 "
			+ " order by c.designation asc")
		*/	
		/*
	@Query("select c.numero, mvts.quantite, art.designation from BlAchat c "
			+ " join c.mvts mvts join mvts.articleFrns af join af.article art")
			*/
@Query("select c from BlAchat c ")		
	List<InventaireArticle> findInventaireArticle();	
}
