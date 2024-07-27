package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoPixResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento2;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento;

import java.util.List;

public interface PagamentoGateway {


    Pagamento salvarDadosPagamento(Pagamento pagamento);

    Pagamento atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Pagamento buscarPagamentoPorId(Long id);

    Pagamento buscarPagamentoPorIdPedido(Long id);

    List<Pagamento> listarPagamentos();

    Pagamento2 efetuarPagamentoViaCartao(Pagamento2 pagamento2);

    PagamentoPixResponse efetuarPagamentoViaPix(Pagamento2 pagamento2);

    String getDetalhesDoPagamento(Long paymentId);
}