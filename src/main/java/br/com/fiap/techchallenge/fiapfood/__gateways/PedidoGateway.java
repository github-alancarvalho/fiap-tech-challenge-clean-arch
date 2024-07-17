package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

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