package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.external.mapper.PedidoMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.external.entities.PedidoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;


public class PedidoGatewayDataMapper extends GenericDaoImpl<PedidoEntity> implements PedidoGateway {

    private EntityManager entityManager;
    private String statusField = "status";

    public PedidoGatewayDataMapper() {
        setClazz(PedidoEntity.class);
        this.entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        setEntityManager(this.entityManager);
    }

    @Override
    public Pedido inserir(Pedido pedido) {
//        entityManager = getConnection().createEntityManager();
//        entityManager.getTransaction().begin();
//        PedidoEntity entity = PedidoMapper.mapToEntity(pedido);
//        ClienteUseCase buscarClienteUseCase = new ClienteUseCase(null);
////        Cliente cliente = buscarClienteUseCase.buscarClientePorCpf(new Cpf(entity.getCliente().getCpf()));
////        entity.setCliente(ClienteMapper.mapToEntity(cliente));
////        entity.setListItens(null);
//        entityManager.persist(entity);
//        entityManager.flush();
//
//        ProdutoUseCase buscarProdutoUseCase = new ProdutoUseCase(null);
//
//        List<ItemPedidoEntity> itens = ItemPedidoMapper.mapListToEntity(pedido.getListItens());
//        for (ItemPedidoEntity item : itens) {
//            item.setPedido(entity);
//            entityManager.persist(item);
//            entityManager.flush();
//            //item.setProduto(ProdutoMapper.mapToEntity(buscarProdutoUseCase.buscarProdutoPorId(item.getProduto().getId()).get()));
//        }
//
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        entity.setListItens(itens);
//        return Optional.ofNullable(PedidoMapper.mapToEntity(entity));


        PedidoEntity entity = save(PedidoMapper.mapToEntity(pedido));
        return PedidoMapper.mapToEntity(entity);
    }

    @Override
    public Pedido atualizarProgresso(Pedido pedido, StatusPedido novoStatus) {
        PedidoEntity entity = findById(pedido.getId());
        entity.setStatus(novoStatus);
        return PedidoMapper.mapToEntity(update(entity));
    }

    @Override
    public Boolean excluir(Long id) {
        PedidoEntity entity = findById(id);
        if (entity != null) {
            delete(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Pedido> listarTudo() {
        return PedidoMapper.mapListToEntity(findAll());
    }


    @Override
    public Pedido buscarPorId(Long id) {
        PedidoEntity entity = findById(id);
        return PedidoMapper.mapToEntity(entity);
    }

    @Override
    public List<Pedido> listarPedidosPorStatus(StatusPedido status) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get(statusField), status.toString()));

        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
//        entityManager.close();
        return PedidoMapper.mapListToEntity(resultList);
    }

    @Override
    public List<Pedido> listarPedidosEmAberto() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        Predicate statusNull = criteriaBuilder.isNotNull(root.get(statusField));
        Predicate statusEntregue = criteriaBuilder.notEqual(root.get(statusField), StatusPedido.ENTREGUE);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.and(statusNull, statusEntregue));


        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
//        entityManager.close();
        return PedidoMapper.mapListToEntity(resultList);
    }

    @Override
    public List<Pedido> listarPedidosAguardandoPagamento() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PedidoEntity> criteriaQuery = criteriaBuilder.createQuery(PedidoEntity.class);
        Root<PedidoEntity> root = criteriaQuery.from(PedidoEntity.class);

        Predicate statusNull = criteriaBuilder.isNull(root.get(statusField));

        criteriaQuery.select(root);
        criteriaQuery.where(statusNull);

        List<PedidoEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();
//        entityManager.close();
        return PedidoMapper.mapListToEntity(resultList);
    }

}

