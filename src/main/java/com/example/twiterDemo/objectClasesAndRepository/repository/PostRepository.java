package com.example.twiterDemo.objectClasesAndRepository.repository;

import com.example.twiterDemo.objectClasesAndRepository.RepositoryClassController;
import com.example.twiterDemo.objectClasesAndRepository.models.Post;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public class PostRepository implements RepositoryClassController {

    public <S extends Post> S save(S post) {
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
        return post;
    }

    public void delete(Post post) {
        entityManager.getTransaction().begin();
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }

    public Optional<Post> findById(Long idPost) {
        return Optional.of(entityManager.find(Post.class, idPost));
    }

}
