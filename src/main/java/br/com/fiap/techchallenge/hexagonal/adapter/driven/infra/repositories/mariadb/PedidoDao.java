package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.PedidoORM;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.ClienteMapper;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.ItemPedidoMapper;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.PedidoMapper;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.produto.BuscarProdutoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ItemPedidoORM;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;


public class PedidoDao extends ConnectionPoolManager implements PedidoRepository {

    private EntityManager entityManager;
    private String statusField = "status";

    @Override
    public Optional<Pedido> inserir(Pedido pedido) {
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        PedidoORM entity = PedidoMapper.mapToEntity(pedido);
        BuscarClienteUseCase buscarClienteUseCase = new BuscarClienteUseCase();
        Cliente cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(entity.getCliente().getCpf())).get();
        entity.setCliente(ClienteMapper.mapToEntity(cliente));
        entity.setListItens(null);
        entityManager.persist(entity);
        entityManager.flush();

        BuscarProdutoUseCase buscarProdutoUseCase = new BuscarProdutoUseCase();

        List<ItemPedidoORM> itens = ItemPedidoMapper.mapListToEntity(pedido.getListItens());
        for (ItemPedidoORM item : itens) {
            item.setPedido(entity);
            entityManager.persist(item);
            entityManager.flush();
            item.setProduto(ProdutoMapper.mapToEntity(buscarProdutoUseCase.buscarProdutoPorId(item.getProduto().getId()).get()));
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entity.setListItens(itens);
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        entityManager = getConnection().createEntityManager();
        PedidoORM entity = entityManager.find(PedidoORM.class, id);
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<Pedido>> listarTudo() {
        entityManager = getConnection().createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPedidos");
        List<PedidoORM> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(list));
    }

    @Override
    public Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status) {
        entityManager = getConnection().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoORM> criteriaQuery = criteriaBuilder.createQuery(PedidoORM.class);
        Root<PedidoORM> root = criteriaQuery.from(PedidoORM.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(statusField), status.toString()));

        List<PedidoORM> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Optional<List<Pedido>> listarPedidosEmAberto() {
        entityManager = getConnection().createEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoORM> criteriaQuery = criteriaBuilder.createQuery(PedidoORM.class);
        Root<PedidoORM> root = criteriaQuery.from(PedidoORM.class);

        Predicate statusNull = criteriaBuilder.isNull(root.get(statusField));
        Predicate statusEntregue = criteriaBuilder.notEqual(root.get(statusField), StatusPedido.ENTREGUE);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.or(statusNull, statusEntregue));


        List<PedidoORM> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapListToEntity(resultList));
    }

    @Override
    public Boolean excluir(Pedido pedido) {
        entityManager = getConnection().createEntityManager();
        PedidoORM entity = entityManager.find(PedidoORM.class, pedido.getId());
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
        PedidoORM entity = PedidoMapper.mapToEntity(pedidoDto);
        entity.setStatus(novoStatus);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));
    }
}

