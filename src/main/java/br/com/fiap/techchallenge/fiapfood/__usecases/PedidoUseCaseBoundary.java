package br.com.fiap.techchallenge.fiapfood.__usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

import java.util.List;

public interface PedidoUseCaseBoundary {

    List<PedidoResponse> buscarPedidosAguardandoPagamento() throws FiapFoodException;

    PedidoResponse inserirPedido(PedidoRequest pedidoRequest) throws FiapFoodException;

    PedidoResponse buscarPedidoPorId(Long id) throws FiapFoodException;

    List<PedidoResponse> buscarTodosPedidos() throws FiapFoodException;

    List<PedidoResponse> buscarPedidosPorStatus(StatusPedido statusPedido) throws FiapFoodException;

    List<PedidoResponse> buscarPedidosEmAberto() throws FiapFoodException;

    PedidoResponse inserirItensNoPedido(PedidoRequest pedidoRequest) throws FiapFoodException;

    PedidoResponse atualizarProgresso(AlterarProgressoPedidoRequest alterarProgressoPedidoRequest) throws FiapFoodException;

    PedidoResponse enviarPedidoParaFilaDePreparacao(Long idPedido) throws FiapFoodException;

    Boolean excluir(Long id) throws FiapFoodException;

    PagamentoResponse checkout(PedidoRequest pedidoRequest);

    Double calcularValorTotalDoPedido(Pedido pedido);
}
