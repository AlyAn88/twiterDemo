package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Reply;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class ReplyRepository implements CrudRepository<Reply, Long> {

    private EntityManager entityManager;
    private EntityManagerFactory eMF;

    public ReplyRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }

    public Reply findByReply(Reply reply) {
        return entityManager.find(Reply.class, reply);
    }

    @Override
    public <S extends Reply> S save(S reply) {
        entityManager.getTransaction().begin();
        entityManager.persist(reply);
        entityManager.getTransaction().commit();
        return reply;
    }

    @Override
    public <S extends Reply> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Reply> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Reply> findAll() {
        return null;
    }

    @Override
    public Iterable<Reply> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Reply entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Reply> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public void close() {
        this.entityManager.close();
        this.eMF.close();
    }
}
