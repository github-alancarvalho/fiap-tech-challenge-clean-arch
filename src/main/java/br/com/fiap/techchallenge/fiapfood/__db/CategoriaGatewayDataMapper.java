package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood.__gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.CategoriaMapper;

import java.util.List;


public class CategoriaGatewayDataMapper extends GenericDaoImpl<CategoriaEntity> implements CategoriaGateway {

    public CategoriaGatewayDataMapper(){
        setClazz(CategoriaEntity.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaMapper.mapListToEntity(findAll());
    }
}

