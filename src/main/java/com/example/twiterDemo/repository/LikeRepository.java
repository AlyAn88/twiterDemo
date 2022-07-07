package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Like;

import com.example.twiterDemo.models.Mention;
import com.example.twiterDemo.models.Post;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public  class LikeRepository implements CrudRepository<Like, Long> {
    private EntityManager entityManager;
    private EntityManagerFactory eMF;

    public LikeRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }

    @Override
    public  <S extends Like> S save(S like) {
        String query = "select s from Like s";
        TypedQuery<Like> queries = entityManager.createQuery(query, Like.class);
        List<Like> likeDbList = queries.getResultList();

        int x = 0;
        for (x = 0; x < likeDbList.size(); x++) {
            if (likeDbList.get(x).getUser().getId().equals(like.getUser().getId())) {
                like.setUser(likeDbList.get(x).getUser());
                like.setId(likeDbList.get(x).getId());
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

    @Override
    public <S extends Like> Iterable<S> saveAll(Iterable<S> like) {
       return null;
    }

    @Override
    public Optional<Like> findById(Long id) {
        return Optional.of(entityManager.find(Like.class, id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Like> findAll() {
        return null;
    }

    @Override
    public Iterable<Like> findAllById(Iterable<Long> longs) {
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
    public void delete(Like entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Like> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
