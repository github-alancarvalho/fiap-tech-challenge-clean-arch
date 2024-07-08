package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoGateway {


    Optional<Pagamento> processarPagamento(Pagamento pagamento);

    Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Optional<Pagamento> buscarPagamentoPorId(Long id);

    Optional<List<Pagamento>> listarPagamentos();
}