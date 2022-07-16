package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Reply;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;


@EnableJpaRepositories
public class ReplyRepository implements RepositoryClassController{

    public <S extends Reply> S save(S reply) {
        entityManager.getTransaction().begin();
        entityManager.persist(reply);
        entityManager.getTransaction().commit();
        return reply;
    }

    public void delete(Reply reply) {
        entityManager.getTransaction().begin();
        entityManager.remove(reply);
        entityManager.getTransaction().commit();
    }

    public Optional<Reply> findById(Long idReply) {
        return Optional.of(entityManager.find(Reply.class, idReply));
    }

}
