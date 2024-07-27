package br.com.fiap.techchallenge.fiapfood.external;

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
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        T t = entityManager.find(clazz, id);
        entityManager.close();
        return t;
    }

    public T find(Object o) {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        T t = entityManager.find(clazz, o);
        entityManager.close();
        return t;
    }

    public List<T> findAll() {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        List<T> list = entityManager.createQuery("from " + clazz.getName()).getResultList();
        entityManager.close();
        return list;
    }

    public T save(final T entity) {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    public T update(final T entity) {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return entity;
    }

    public void delete(final T entity) {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(final long entityId) {
        entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        final T entity = entityManager.find(clazz, entityId);
//        delete(entity);
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
