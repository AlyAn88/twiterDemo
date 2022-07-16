package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Like;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;



@EnableJpaRepositories
public class LikeRepository implements RepositoryClassController {

    public <S extends Like> S save(S like) {
        String query = "select s from Like s";
        TypedQuery<Like> queries = entityManager.createQuery(query, Like.class);
        List<Like> likeDbList = queries.getResultList();

        for (Like value : likeDbList) {
            if (value.getUser().getId().equals(like.getUser().getId())) {

                like.setUser(value.getUser());
                like.setId(value.getId());

                entityManager.getTransaction().begin();
                entityManager.merge(like);
                entityManager.getTransaction().commit();

                return like;
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(like);
        entityManager.getTransaction().commit();

        return like;
    }
    public Optional<Like> findById(Long idLike) {
        return Optional.of(entityManager.find(Like.class, idLike));
    }

}
