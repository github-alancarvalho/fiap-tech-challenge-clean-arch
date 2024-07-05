package br.com.fiap.techchallenge.fiapfood._infra.gateway;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.__gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaRepository;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.CategoriaMapper;

import java.util.List;


public class CategoriaGatewayJPA implements CategoriaGateway {

    private final CategoriaRepository repository;


//    private EntityManagerFactory emf;
//    private EntityManager entityManager;

//    public CategoriaGatewayJPA(CategoriaRepository repository, DbConnection connection){
//        this.repository = repository;
//        this.emf = connection.getConnection();
//    }

    public CategoriaGatewayJPA(CategoriaRepository repository){
        this.repository = repository;
//        this.emf = connection.getConnection();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return CategoriaMapper.mapListToEntity(repository.findAll());

//        entityManager = emf.createEntityManager();
//        Query query = entityManager.createNamedQuery("findAllCategorias");
//        List<CategoriaEntity> list = query.getResultList();
//        entityManager.close();
        //return Optional.ofNullable(CategoriaMapper.mapListToEntity(list));
    }
}

