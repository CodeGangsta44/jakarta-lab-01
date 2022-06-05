package edu.kpi.model.facade.impl;

import edu.kpi.model.User;
import edu.kpi.model.facade.AbstractFacade;
import edu.kpi.model.facade.UserFacadeLocal;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public UserFacade() {

        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {

        return entityManager;
    }

    @Override
    public Optional<User> findByUsername(final String username) {

        return getEntityManager().createNamedQuery("findByUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findAny();
    }
}
