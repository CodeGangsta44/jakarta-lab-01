package edu.kpi.model.facade;

import jakarta.persistence.EntityManager;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(final Class<T> entityClass) {

        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(final T entity) {

        getEntityManager().persist(entity);
    }

    public void update(final T entity) {

        getEntityManager().merge(entity);
    }

    public void delete(final T entity) {

        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T findById(final Object id) {

        return getEntityManager().find(entityClass, id);
    }
}
