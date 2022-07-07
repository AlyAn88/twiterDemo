package com.example.twiterDemo.repository;


import com.example.twiterDemo.models.TwiterUsers;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;

import java.util.*;

public class TwiterUsersRepository implements CrudRepository<TwiterUsers, Long> {
    private EntityManager entityManager;
    private EntityManagerFactory eMF;


    public TwiterUsersRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }


    @Override
    public <S extends TwiterUsers> S save(S twiterUsers) {
        String query = "select s from TwiterUsers s";
        TypedQuery<TwiterUsers> queries = entityManager.createQuery(query, TwiterUsers.class);//.setParameter("id", twiterUsers.getId());
        List<TwiterUsers> twiterUsersDbList = queries.getResultList();


        int x = 0;
        for (x = 0; x < twiterUsersDbList.size(); x++) {
            if (twiterUsersDbList.get(x).getUserName().equals(twiterUsers.getUserName())) {
                twiterUsers.getUser().setId(twiterUsersDbList.get(x).getUser().getId());
                twiterUsers.setId(twiterUsersDbList.get(x).getId());

                entityManager.getTransaction().begin();
                entityManager.merge(twiterUsers);
                entityManager.getTransaction().commit();

                return null;
            }
        }
        entityManager.getTransaction().begin();
        entityManager.persist(twiterUsers);
        entityManager.getTransaction().commit();
        return twiterUsers;
    }

    @Override
    public <S extends TwiterUsers> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TwiterUsers> findById(Long userNameId) {
        return Optional.of(entityManager.find(TwiterUsers.class, userNameId));
    }

    public Long findIdByUserName(String userName) {
        String query = "select s.id from TwiterUsers s where s.userName = :userName";
        TypedQuery<Long> queries = entityManager.createQuery(query, Long.class).setParameter("userName", userName);

        return queries.getSingleResult();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TwiterUsers> findAll() {
        return null;
    }

    @Override
    public Iterable<TwiterUsers> findAllById(Iterable<Long> longs) {
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
    public void delete(TwiterUsers entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TwiterUsers> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public TwiterUsers addMention(TwiterUsers twiterUsers) {
        entityManager.getTransaction().begin();
        entityManager.merge(twiterUsers);
        entityManager.getTransaction().commit();
        return twiterUsers;

    }


    public void close() {
        this.entityManager.close();
        this.eMF.close();
    }
}
