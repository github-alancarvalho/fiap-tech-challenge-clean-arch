package br.com.fiap.techchallenge.fiapfood._infra_apagar.gateway_apagar;

import br.com.fiap.techchallenge.fiapfood.__db.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood.__db.GenericDaoImpl;
import br.com.fiap.techchallenge.fiapfood._infra_apagar.persistence.CategoriaRepository_apagar;
import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;

public class CategoriaRepositoryApagarImpl extends GenericDaoImpl<CategoriaEntity> implements CategoriaRepository_apagar {

    public CategoriaRepositoryApagarImpl(){
        setClazz(CategoriaEntity.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

}
