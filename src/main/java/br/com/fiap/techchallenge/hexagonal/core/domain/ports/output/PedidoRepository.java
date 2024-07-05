package br.com.fiap.techchallenge.hexagonal.core.domain.ports.output;

import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {

    Optional<Pedido> inserir(Pedido pedido);

    Optional<Pedido> atualizarProgresso(Pedido pedido, StatusPedido novoStatus);

    Boolean excluir(Pedido pedido);

    Optional<Pedido> buscarPorId(Long id);

    Optional<List<Pedido>> listarTudo();

    Optional<List<Pedido>> listarPedidosPorStatus(StatusPedido status);

    Optional<List<Pedido>> listarPedidosEmAberto();
}