package br.com.fiap.techchallenge.fiapfood.__db;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;


@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class GenericDaoImpl<T extends Serializable> {

    private Class<T> clazz;
    private EntityManager entityManager;

    public final void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public final void setClazz(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findById(final long id) {
        return entityManager.find(clazz, id);
    }

    public T find(Object o) {
        return entityManager.find(clazz, o);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public T save(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return entity;
    }

    public T update(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return entity;
    }

    public void delete(final T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public void deleteById(final long entityId) {
        final T entity = findById(entityId);
        delete(entity);
    }

}
