package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood.__db.mapper.ItemPedidoMapper;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.stream.Collectors;


public class ItemPedidoGatewayDataMapper extends GenericDaoImpl<ItemPedidoEntity> implements ItemPedidoGateway {

    private EntityManager entityManager;

    public ItemPedidoGatewayDataMapper(){
        setClazz(ItemPedidoEntity.class);
        this.entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        setEntityManager(this.entityManager);
    }

    @Override
    public List<ItemPedido> inserir(List<ItemPedido> itens) {

        List<ItemPedidoEntity> lista = itens.stream().map(item ->
                save(ItemPedidoMapper.mapToEntity(item))
        ).collect(Collectors.toList());

        return ItemPedidoMapper.mapListToORM(lista);

    }
}

