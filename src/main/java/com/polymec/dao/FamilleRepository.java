package com.polymec.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import com.polymec.domain.Famille;

//@Repository
public interface FamilleRepository extends JpaRepository<Famille, Long> {
    // One fancy aspect of Spring
    // Data’s Repository abstraction is that when you use the common naming convention such as findByFirstName and
    // findByFirstNameAndLastName, you don’t need to provide Spring Data JPA with the named query. Instead, Spring Data
    // JPA will “infer” and construct the query for you based on the method name.
    //List<Article> findByReference(String reference);

    @Query("select distinct c from Famille c "
            + " join c.articles art join art.articleFrns artFrs "
            + " where c.articles is not empty and artFrs.quantite != 0 and artFrs.sr = 0 "
            + " order by c.designation asc")
    List<Famille> findAllValid();
    //Page<Article> findByReference(String reference,Pageable pageable);	
    //List<Article> findByFirstNameAndLastName(String firstName, String lastName);
}
