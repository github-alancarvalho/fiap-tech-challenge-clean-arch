package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.external.mapper.ItemPedidoMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood.external.entities.ItemPedidoEntity;
import jakarta.persistence.EntityManager;

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

