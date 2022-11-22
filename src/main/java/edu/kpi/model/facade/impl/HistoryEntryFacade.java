package edu.kpi.model.facade.impl;

import edu.kpi.model.Connection;
import edu.kpi.model.HistoryEntry;
import edu.kpi.model.facade.AbstractFacade;
import edu.kpi.model.facade.HistoryEntryFacadeLocal;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class HistoryEntryFacade extends AbstractFacade<HistoryEntry> implements HistoryEntryFacadeLocal {

    @PersistenceContext
    private EntityManager entityManager;

    public HistoryEntryFacade() {

        super(HistoryEntry.class);
    }

    @Override
    protected EntityManager getEntityManager() {

        return entityManager;
    }

    @Override
    public List<HistoryEntry> findByConnection(final Connection connection) {

        return getEntityManager().createNamedQuery("findByConnection", HistoryEntry.class)
                .setParameter("connection", connection)
                .getResultList();
    }
}
