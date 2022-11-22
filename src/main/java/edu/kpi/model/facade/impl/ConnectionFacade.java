package edu.kpi.model.facade.impl;

import edu.kpi.model.Connection;
import edu.kpi.model.User;
import edu.kpi.model.facade.AbstractFacade;
import edu.kpi.model.facade.ConnectionFacadeLocal;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ConnectionFacade extends AbstractFacade<Connection> implements ConnectionFacadeLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public ConnectionFacade() {

        super(Connection.class);
    }

    @Override
    protected EntityManager getEntityManager() {

        return entityManager;
    }

    @Override
    public List<Connection> findByUser(final User user) {

        return getEntityManager().createNamedQuery("findByUser", Connection.class)
                .setParameter("user", user)
                .getResultList();
    }
}
