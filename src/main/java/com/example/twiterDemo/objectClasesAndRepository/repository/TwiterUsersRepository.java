package com.example.twiterDemo.objectClasesAndRepository.repository;


import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.TwiterUsers;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import javax.persistence.*;

import java.util.*;


@EnableJpaRepositories
public class TwiterUsersRepository implements RepositoryClassController {


    public <S extends TwiterUsers> S save(S twiterUsers) {
        String query = "select s from TwiterUsers s";
        TypedQuery<TwiterUsers> queries = entityManager.createQuery(query, TwiterUsers.class);
        List<TwiterUsers> twiterUsersDbList = queries.getResultList();


        for (TwiterUsers users : twiterUsersDbList) {
            if (users.getUserName() != null) {
                if (users.getUserName().equals(twiterUsers.getUserName())) {
                    twiterUsers.setId(users.getId());

                    entityManager.getTransaction().begin();
                    entityManager.merge(twiterUsers);
                    entityManager.getTransaction().commit();

                    return twiterUsers;
                }
            }
        }

        entityManager.getTransaction().begin();
        entityManager.persist(twiterUsers);
        entityManager.getTransaction().commit();
        return twiterUsers;
    }

    public void delete(TwiterUsers twiterUsers) {
        entityManager.getTransaction().begin();
        entityManager.remove(twiterUsers);
        entityManager.getTransaction().commit();
    }


    public Optional<TwiterUsers> findById(Long userNameId) {
        return Optional.of(entityManager.find(TwiterUsers.class, userNameId));
    }


    public List<TwiterUsers> findAll() {
        return entityManager.createQuery
                        ("select s from TwiterUsers s", TwiterUsers.class)
                .getResultList();
    }

}
