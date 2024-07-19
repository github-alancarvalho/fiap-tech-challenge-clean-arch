package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

import java.util.List;

public interface ItemPedidoGateway {

    List<ItemPedido> inserir(List<ItemPedido> itens);


}