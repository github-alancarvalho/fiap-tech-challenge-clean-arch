package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.dto.PagamentoPixResponse;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;

import java.util.List;

public interface PagamentoGateway {


    Pagamento salvarDadosPagamento(Pagamento pagamento);

    Pagamento atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Pagamento buscarPagamentoPorId(Long id);

    Pagamento buscarPagamentoPorIdPedido(Long id);

    List<Pagamento> listarPagamentos();

    Pagamento efetuarPagamentoViaCartao(Pagamento pagamento2);

    PagamentoPixResponse efetuarPagamentoViaPix(Pagamento pagamento2);

    String getDetalhesDoPagamento(Long paymentId);
}