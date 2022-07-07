package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Mention;
import com.example.twiterDemo.models.Post;
import com.example.twiterDemo.models.TwiterUsers;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class MentionRepository implements CrudRepository<Mention, Long> {

    private EntityManager entityManager;
    private EntityManagerFactory eMF;

    public MentionRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }

    @Override
    public <S extends Mention> S save(S mention) {
        String query = "select s from Mention s";
        TypedQuery<Mention> queries = entityManager.createQuery(query, Mention.class);//.setParameter("id", twiterUsers.getId());
        List<Mention> mentionDbList = queries.getResultList();

        int x = 0;
        for (x = 0; x < mentionDbList.size(); x++) {
            if (mentionDbList.get(x).getUser().getId().equals(mention.getUser().getId())) {
                mention.setUser(mentionDbList.get(x).getUser());
                mention.setId(mentionDbList.get(x).getId());
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

    @Override
    public <S extends Mention> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Mention> findById(Long id) {
        return Optional.of(entityManager.find(Mention.class, id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Mention> findAll() {
        return null;
    }

    @Override
    public Iterable<Mention> findAllById(Iterable<Long> longs) {
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
    public void delete(Mention entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Mention> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
