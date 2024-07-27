package br.com.fiap.techchallenge.fiapfood.core.usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood.core.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento2;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;

import java.util.List;

public interface PagamentoUseCaseBoundary {
    PagamentoResponse buscarPagamentoPorId(Long id) throws FiapFoodException;

    PagamentoResponse buscarPagamentoPorIdPedido(Long id) throws FiapFoodException;

    List<PagamentoResponse> buscarTodosPagamentos() throws FiapFoodException;

    PagamentoResponse salvarDadosPagamento(PagamentoRequest pagamentoRequest) throws FiapFoodException;


    PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento status) throws FiapFoodException;

    Pagamento2 prepararPagamento(Cliente cliente, Pedido pedido, Double valorTotalPedido, CartaoCredito cartaoCredito);

    PagamentoResponse efetuarPagamentoViaCartao(Pagamento2 pagamento2);

    PagamentoPixResponse efetuarPagamentoViaPix(Pagamento2 pagamento2);

    String  getDetalhesDoPagamento(Long idDoPagamento);
}
