package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.adapters.gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.external.mapper.CategoriaMapper;
import br.com.fiap.techchallenge.fiapfood.external.entities.CategoriaEntity;

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

