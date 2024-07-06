package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood.__gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.CategoriaMapper;

import java.util.List;


public class CategoriaGatewayJPAClean extends GenericDaoImpl<CategoriaDataMapper> implements CategoriaGateway {

    public CategoriaGatewayJPAClean(){
        setClazz(CategoriaDataMapper.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaMapper.mapListToEntity(findAll());
    }
}

