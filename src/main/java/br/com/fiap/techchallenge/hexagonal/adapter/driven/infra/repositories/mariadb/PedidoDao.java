package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.__db.PedidoEntity;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.ItemPedidoMapper;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.PedidoMapper;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.__usecases.ClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__db.ItemPedidoEntity;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class PedidoDao extends ConnectionPoolManager implements PedidoGateway {

    private EntityManager entityManager;
    private String statusField = "status";

    @Override
    public Optional<Pedido> inserir(Pedido pedido) {
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        PedidoEntity entity = PedidoMapper.mapToEntity(pedido);
        ClienteUseCase buscarClienteUseCase = new ClienteUseCase(null);
//        Cliente cliente = buscarClienteUseCase.buscarClientePorCpf(new Cpf(entity.getCliente().getCpf()));
//        entity.setCliente(ClienteMapper.mapToEntity(cliente));
//        entity.setListItens(null);
        entityManager.persist(entity);
        entityManager.flush();

        ProdutoUseCase buscarProdutoUseCase = new ProdutoUseCase(null);

        List<ItemPedidoEntity> itens = ItemPedidoMapper.mapListToEntity(pedido.getListItens());
        for (ItemPedidoEntity item : itens) {
            item.setPedido(entity);
            entityManager.persist(item);
            entityManager.flush();
            //item.setProduto(ProdutoMapper.mapToEntity(buscarProdutoUseCase.buscarProdutoPorId(item.getProduto().getId()).get()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entity.setListItens(itens);
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        entityManager = getConnection().createEntityManager();
        PedidoEntity entity = entityManager.find(PedidoEntity.class, id);
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<Pedido>> listarTudo() {
        entityManager = getConnection().createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPedidos");
        List<PedidoEntity> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(list));
    }

    @Override
    public Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status) {
        entityManager = getConnection().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(statusField), status.toString()));

        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<Pedido>> listarPedidosEmAberto() {
        entityManager = getConnection().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        Predicate statusNull = criteriaBuilder.isNull(root.get(statusField));
        Predicate statusEntregue = criteriaBuilder.notEqual(root.get(statusField), StatusPedido.ENTREGUE);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.or(statusNull, statusEntregue));


        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Boolean excluir(Pedido pedido) {
        entityManager = getConnection().createEntityManager();
        PedidoEntity entity = entityManager.find(PedidoEntity.class, pedido.getId());
        if (entity != null) {
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
    public Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido novoStatus) {

        Pedido pedidoDto = buscarPorId(pedido.getId()).get();

        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        PedidoEntity entity = PedidoMapper.mapToEntity(pedidoDto);
        entity.setStatus(novoStatus);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }
}

