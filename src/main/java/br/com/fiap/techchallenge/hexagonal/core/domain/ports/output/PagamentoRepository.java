package br.com.fiap.techchallenge.hexagonal.core.domain.ports.output;

import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoRepository {


    Optional<Pagamento> processarPagamento(Pagamento pagamento);

    Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Optional<Pagamento> buscarPagamentoPorId(Long id);

    Optional<List<Pagamento>> listarPagamentos();
}