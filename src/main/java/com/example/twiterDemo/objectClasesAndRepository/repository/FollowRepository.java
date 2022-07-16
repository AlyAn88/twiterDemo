package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Follow;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public class FollowRepository implements RepositoryClassController {

    public <S extends Follow> S save(S follow) {
        String query = "select s from Follow s";
        TypedQuery<Follow> queries = entityManager.createQuery(query, Follow.class);
        List<Follow> followDbList = queries.getResultList();


        for (Follow value : followDbList) {
            if (value.getUser().getId().equals(follow.getUser().getId())) {

                follow.setUser(value.getUser());
                follow.setId(value.getId());

                entityManager.getTransaction().begin();
                entityManager.merge(follow);
                entityManager.getTransaction().commit();

                return follow;
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(follow);
        entityManager.getTransaction().commit();

        return follow;
    }

    public Optional<Follow> findById(Long idFollow) {
        return Optional.of(entityManager.find(Follow.class, idFollow));
    }

    public void delete(Follow follow) {
        entityManager.getTransaction().begin();
        entityManager.remove(follow);
        entityManager.getTransaction().commit();
    }

    public List<Follow> findAllWhoFallowYou(Long userId) {
        return entityManager.createQuery
                        ("SELECT d FROM Follow d where d.followThisUser.id= :id", Follow.class).setParameter("id",userId)
                .getResultList();
    }

}
