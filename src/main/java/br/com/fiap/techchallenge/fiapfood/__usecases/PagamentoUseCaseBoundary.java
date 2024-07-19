package br.com.fiap.techchallenge.fiapfood.__usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento2;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;

import java.util.List;

public interface PagamentoUseCaseBoundary {
    PagamentoResponse buscarPagamentoPorId(Long id) throws FiapFoodException;

    PagamentoResponse buscarPagamentoPorIdPedido(Long id) throws FiapFoodException;

    List<PagamentoResponse> buscarTodosPagamentos() throws FiapFoodException;

    PagamentoResponse processarPagamento(PagamentoRequest pagamentoRequest) throws FiapFoodException;

//    PagamentoResponse receberConfirmacaoDePagamento(Long idPagamento);

    PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento status) throws FiapFoodException;

    Pagamento2 prepararPagamento(Cliente cliente, Pedido pedido, Double valorTotalPedido, CartaoCredito cartaoCredito);

    String gerarTokenCartaoCredito(CartaoCredito cartaoCredito);

    PagamentoResponse efetuarPagamento(Pagamento2 pagamento2);
}
