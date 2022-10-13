package com.example.predProject.dao;

import com.example.predProject.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

//    static {
//        User user = new User();
//        user.setName("name 1");
//        user.setLastName("name 2");
//        user.setAge(12);
//        entityManager.persist(new User());
//    }

    @Override
    public List<User> allUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void delete(User user) {
//        entityManager.remove(user);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        entityManager.flush();
    }

    @Override
    @Transactional
    public void edit(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}
