package com.polymec.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polymec.model.ArticleFrns;

//@Repository
public interface ArticleFrnsRepository extends JpaRepository<ArticleFrns, Long> {
	// One fancy aspect of Spring
	// Data’s Repository abstraction is that when you use the common naming convention such as findByFirstName and
	// findByFirstNameAndLastName, you don’t need to provide Spring Data JPA with the named query. Instead, Spring Data
	// JPA will “infer” and construct the query for you based on the method name.
    //List<ArticleFrns> findByReference(String reference);
	//List<Article> findAllWithDetail();
    //Page<Article> findByReference(String reference,Pageable pageable);	
    //List<Article> findByFirstNameAndLastName(String firstName, String lastName);
	//@Query("select c from ArticleFrns c where c.sr = 0 and c.quantite > 0 order by c.article.reference, c.article.designation")
	
	@Query("select c from ArticleFrns c where c.sr = 0 and c.quantite > 0 order by c.article.reference, c.article.designation")
	List<ArticleFrns> findAllValid();
	
	@Query("select c from ArticleFrns c where c.sr = 0 and c.quantite > 0 and c.article.famille.id = ?1 order by c.article.reference, c.article.designation")
	List<ArticleFrns> findByFamille(Long id);	
}
