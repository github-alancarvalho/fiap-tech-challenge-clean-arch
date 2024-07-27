package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;

import java.util.List;

public interface PedidoGateway {

    Pedido inserir(Pedido pedido);

    Pedido atualizarProgresso(Pedido pedido, StatusPedido novoStatus);

    Boolean excluir(Long id);

    Pedido buscarPorId(Long id);

    List<Pedido> listarTudo();

    List<Pedido> listarPedidosPorStatus(StatusPedido status);

    List<Pedido> listarPedidosEmAberto();


    List<Pedido> listarPedidosAguardandoPagamento();
}