package br.com.fiap.techchallenge.fiapfood._infra.gateway;

import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaRepository;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.ConnectionPoolManager;

public class CategoriaRepositoryImpl extends GenericDaoImpl<CategoriaEntity> implements CategoriaRepository {

    public CategoriaRepositoryImpl(){
        setClazz(CategoriaEntity.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

}
