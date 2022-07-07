package com.example.twiterDemo.repository;

import com.example.twiterDemo.models.Mention;
import com.example.twiterDemo.models.User;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;


public class UserRepository implements CrudRepository<User, Long> {

    private EntityManager entityManager;
    private EntityManagerFactory eMF;

    public UserRepository() {
        this.eMF = Persistence.createEntityManagerFactory("user-pu");
        this.entityManager = this.eMF.createEntityManager();
    }

    @Override
    public <S extends User> S save(S user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
        return user;
    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.of(entityManager.find(User.class, id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<User> findAll() {
        return null;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Long> longs) {
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
    public void delete(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }
    @Override
    public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    public User add(User user, String userName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        Class userClass = user.getClass();
//
//        Field field = userClass.getField("user");
//        field.set(user, userName);
        save(user);

       /* entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();*/
        return user;
    }

    public List<User> findUsersByFistName(String keyword) {
        Query query = entityManager.createQuery("select * from user where first_name like'" + keyword + "%'");
        return query.getResultList();
    }

    public List<User> findUsersByLastName(String keyword) {
        Query query = entityManager.createQuery("select * from user where last_name like'" + keyword + "%'");
        return query.getResultList();
    }

    public void remove(User user) {
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.eMF.close();
    }


}
