package br.com.fiap.techchallenge.fiapfood._infra.gateway_apagar;

import br.com.fiap.techchallenge.fiapfood.__db.CategoriaDataMapper;
import br.com.fiap.techchallenge.fiapfood.__db.GenericDaoImpl;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaRepository_apagar;
import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;

public class CategoriaRepositoryApagarImpl extends GenericDaoImpl<CategoriaDataMapper> implements CategoriaRepository_apagar {

    public CategoriaRepositoryApagarImpl(){
        setClazz(CategoriaDataMapper.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

}
