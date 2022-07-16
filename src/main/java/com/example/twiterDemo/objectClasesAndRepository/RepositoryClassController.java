package com.example.twiterDemo.objectClasesAndRepository;

import javax.persistence.*;
import java.util.Random;


public interface RepositoryClassController {

    EntityManagerFactory eMF = Persistence.createEntityManagerFactory("user-pu");
    EntityManager entityManager = eMF.createEntityManager();

    default long getRandomNumber() {
        long min = 1000000000L;
        long max = 99999999999L;
        Random random = new Random();
        return random.nextLong() % (max - min) + max;
    }
}
