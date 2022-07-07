package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.Reply;
import com.example.twiterDemo.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PostRepository implements CrudRepository<Post, Long> {

    private EntityManager entityManager;
    private EntityManagerFactory eMF;

    public PostRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }

    public Post add(Post post) {
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
        return post;
    }

    public List<Post> postsListByUser (User user){
        Query query = entityManager.createQuery("SELECT s FROM Post s where s.user = "+ user +"order by s.timestamp desc");
        return query.getResultList();
    }


    @Override
    public <S extends Post> S save(S post) {
        entityManager.getTransaction().begin();
        entityManager.merge(post);
        entityManager.getTransaction().commit();
        return post;
    }
    public <S extends Post> S updateReplyPost(S post, Set<Reply> reply) {
        entityManager.getTransaction().begin();
        entityManager.persist(reply);
        entityManager.getTransaction().commit();
        return post;
    }
    public Post findPost (Long id){
        return entityManager.find(Post.class, id);
    }



    @Override
    public <S extends Post> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.of(entityManager.find(Post.class, id));
    }


    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Long> longs) {
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
    public void delete(Post post) {
        entityManager.getTransaction().begin();
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }
    public void remove(Post post) {
        entityManager.getTransaction().begin();
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }



    public void close() {
        this.entityManager.close();
        this.eMF.close();
    }
}
