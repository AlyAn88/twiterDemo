package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.User;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public class UserRepository implements RepositoryClassController {

    public <S extends User> S save(S user) {
        String query = "select s from User s";
        TypedQuery<User> queries = entityManager.createQuery(query, User.class);
        List<User> usersDbList = queries.getResultList();

        for (User value : usersDbList) {
            if (value.getId().equals(user.getId())) {
                user.setId(value.getId());

                entityManager.getTransaction().begin();
                entityManager.merge(user);
                entityManager.getTransaction().commit();

                return user;
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    public void delete(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    public Optional<User> findById(Long idUser) {
        return Optional.of(entityManager.find(User.class, idUser));
    }


}
