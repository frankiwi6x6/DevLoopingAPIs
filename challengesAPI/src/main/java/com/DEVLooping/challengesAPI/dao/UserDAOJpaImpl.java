package com.DEVLooping.challengesAPI.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD:challengesAPI/src/main/java/com/DEVLooping/challengesAPI/dao/UserDAOJpaImpl.java
import com.DEVLooping.challengesAPI.entity.User;
=======
import com.DEVLooping.cruddemo.entity.User;

>>>>>>> parent of 9582320 (updated: login and register functions):src/main/java/com/DEVLooping/cruddemo/dao/UserDAOJpaImpl.java
import java.util.List;

@Repository
public class UserDAOJpaImpl implements UserDAO {

    private EntityManager entityManager;

    public UserDAOJpaImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User", User.class);
        return theQuery.getResultList();
    }

    @Override
    public User findById(int theId) {
        return entityManager.find(User.class, theId);
    }

    @Override
    public User findByUsername(String theUsername) {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User WHERE username=:username", User.class);
        theQuery.setParameter("username", theUsername);

        try {
            return theQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User loginUser(String theUsername, String thePassword) {
        TypedQuery<User> theQuery = entityManager
                .createQuery("FROM User WHERE username=:username AND password=:password", User.class);
        theQuery.setParameter("username", theUsername);
        theQuery.setParameter("password", thePassword);

        try {
            return theQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User save(User theUser) {
        return entityManager.merge(theUser);
    }

    @Override
    public void deleteById(int theId) {
        User dbUser = entityManager.find(User.class, theId);
        entityManager.remove(dbUser);
    }
}
