package br.com.fiap.techchallenge.fiapfood._infra.gateway_apagar;

import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.List;


public abstract class AbstractJpaDao<T extends Serializable> {

    private Class<T> clazz;
    private EntityManager entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findById(final long id) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T save(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final long entityId) {
        final T entity = findById(entityId);
        delete(entity);
    }

}

