package br.com.fiap.techchallenge.fiapfood._infra.gateway;

import br.com.fiap.techchallenge.fiapfood._infra.persistence.GenericDao;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;
import java.util.List;


@Scope( BeanDefinition.SCOPE_PROTOTYPE )
//public class GenericDaoImpl< T extends Serializable >
//        extends AbstractJpaDao< T > implements GenericDao< T >{
//}
public class GenericDaoImpl< T extends Serializable >
        implements GenericDao< T >{

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
