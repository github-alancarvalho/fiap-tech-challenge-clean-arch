package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.external.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.external.entities.ProdutoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;


public class ProdutoGatewayImpl extends GenericDaoImpl<ProdutoEntity> implements ProdutoGateway {

    private EntityManager entityManager;

    public ProdutoGatewayImpl(){
        setClazz(ProdutoEntity.class);
        this.entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        setEntityManager(this.entityManager);
    }

    @Override
    public Produto inserir(Produto produto) {
        ProdutoEntity entity = save(ProdutoMapper.mapToEntity(produto));
        return ProdutoMapper.mapToEntity(entity);
    }


    @Override
    public Produto atualizar(Produto produto) {
        ProdutoEntity entity = update(ProdutoMapper.mapToEntity(produto));
        return ProdutoMapper.mapToEntity(entity);
    }

    @Override
    public Boolean excluir(Long id) {

        deleteById(id);
        return true;
//        ProdutoEntity entity = findById(id);
//        if (entity != null){
//            delete(entity);
//            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public List<Produto> listarTudo() {
        return ProdutoMapper.mapListToEntity(findAll());
    }


    @Override
    public Produto buscarPorId(Long id) {
        ProdutoEntity entity = findById(id);
        return ProdutoMapper.mapToEntity(entity);
    }

    @Override
    public List<Produto> listarPorCategoria(Categoria categoria) {


        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoEntity> criteriaQuery = criteriaBuilder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> root = criteriaQuery.from(ProdutoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoriaEntity").get("id"), categoria.getId()));

        List<ProdutoEntity> resultList = this.entityManager.createQuery(criteriaQuery).getResultList();
//        entityManager.close();
        return ProdutoMapper.mapListToEntity(resultList);
    }
}

