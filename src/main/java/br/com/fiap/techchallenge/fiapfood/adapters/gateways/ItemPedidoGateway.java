package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.core.entity.ItemPedido;

import java.util.List;

public interface ItemPedidoGateway {

    List<ItemPedido> inserir(List<ItemPedido> itens);


}