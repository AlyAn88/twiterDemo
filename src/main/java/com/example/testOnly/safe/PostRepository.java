package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PostRepository {

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

    public void remove(Post post) {
        entityManager.getTransaction().begin();
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }

    public Post updateStudent(Post post, User newUser) {
        entityManager.getTransaction().begin();
        post.setUser(newUser);
        add(post);
        entityManager.getTransaction().commit();
        return post;
    }

}
