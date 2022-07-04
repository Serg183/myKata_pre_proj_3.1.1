package com.userSpringBoot.dao;

import com.userSpringBoot.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManger;

    @Override
    public List<User> listUser() {
        return entityManger.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManger.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManger.merge(user);
    }

    @Override
    public void removeUser(Long id) {
        User user = entityManger.find(User.class, id);
        entityManger.remove(user);
    }

    @Override
    public User getUserById(Long id) {
        User user = entityManger.find(User.class, id);
        entityManger.detach(user);
        return user;
    }
}
