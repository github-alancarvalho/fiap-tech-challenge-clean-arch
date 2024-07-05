package br.com.fiap.techchallenge.fiapfood._infra.persistence;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<T extends Serializable> {

    void setClazz(final Class<T> clazzToSet);
    T findById(final long id);
    List<T> findAll();
    T save(final T entity);
    T update(final T entity);
    void delete(final T entity);
    void deleteById(final long id);

}
