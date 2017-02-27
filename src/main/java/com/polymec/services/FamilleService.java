package com.polymec.services;

import java.util.List;

<<<<<<< HEAD:src/main/java/com/polymec/services/FamilleService.java
import com.polymec.domain.Famille;
=======
import com.polymec.domain.db.Famille;
>>>>>>> develop:src/main/java/com/polymec/services/FamilleService.java

public interface FamilleService {

    List<Famille> findAllValid();

    Famille findById(Long id);
<<<<<<< HEAD:src/main/java/com/polymec/services/FamilleService.java
=======
    
    Famille save(Famille fml);
>>>>>>> develop:src/main/java/com/polymec/services/FamilleService.java
}
