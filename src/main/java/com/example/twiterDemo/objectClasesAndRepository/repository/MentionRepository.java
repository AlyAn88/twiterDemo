package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Mention;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@EnableJpaRepositories
public class MentionRepository implements RepositoryClassController {

    public <S extends Mention> S save(S mention) {
        String query = "select s from Mention s";
        TypedQuery<Mention> queries = entityManager.createQuery(query, Mention.class);//.setParameter("id", twiterUsers.getId());
        List<Mention> mentionDbList = queries.getResultList();

        for (Mention value : mentionDbList) {
            if (value.getUser().getId().equals(mention.getUser().getId())) {
                mention.setUser(value.getUser());
                mention.setId(value.getId());
                entityManager.getTransaction().begin();
                entityManager.merge(mention);
                entityManager.getTransaction().commit();

                return null;
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(mention);
        entityManager.getTransaction().commit();

        return mention;
    }

    public Optional<Mention> findById(Long idMention) {
        return Optional.of(entityManager.find(Mention.class, idMention));
    }

}
