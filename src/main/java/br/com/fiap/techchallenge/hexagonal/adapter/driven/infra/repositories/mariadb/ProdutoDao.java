package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ProdutoORM;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class ProdutoDao extends ConnectionPoolManager implements ProdutoRepository {

    private EntityManager entityManager;

    @Override
    public Optional<Produto> inserir(Produto produto) {
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        ProdutoORM entity = ProdutoMapper.mapToEntity(produto);
        CategoriaEntity categoriaEntity = entityManager.find(CategoriaEntity.class, entity.getCategoriaEntity().getId());
        entity.setCategoria(categoriaEntity);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        entityManager = getConnection().createEntityManager();
        ProdutoORM entity = entityManager.find(ProdutoORM.class, id);
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<Produto>> listarPorCategoria(Categoria categoria) {
        entityManager = getConnection().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProdutoORM> criteriaQuery = criteriaBuilder.createQuery(ProdutoORM.class);
        Root<ProdutoORM> root = criteriaQuery.from(ProdutoORM.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("categoria").get("id"), categoria.getId()));

        List<ProdutoORM> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<Produto>> listarTudo() {
        entityManager = getConnection().createEntityManager();
        Query query = entityManager.createNamedQuery("findAllProdutos");
        List<ProdutoORM> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapListToEntity(list));
    }

    @Override
    public Boolean excluir(Produto produto) {
        entityManager = getConnection().createEntityManager();
        ProdutoORM entity = entityManager.find(ProdutoORM.class, produto.getId());
        if (entity != null){
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        } else {
            entityManager.close();
            return false;
        }
    }

    @Override
    public Optional<Produto> atualizar(Produto produto) {
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        ProdutoORM entity = ProdutoMapper.mapToEntity(produto);
        CategoriaEntity categoriaEntity = entityManager.find(CategoriaEntity.class, entity.getCategoriaEntity().getId());
        entity.setCategoria(categoriaEntity);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(ProdutoMapper.mapToEntity(entity));
    }
}

