package br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PagamentoRepository;

import java.util.Optional;


public class AtualizarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;

    public AtualizarPagamentoUseCase() {
        this.pagamentoRepository =  DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> atualizarProgressoPagamento(Pagamento pagamento, StatusPagamento status) {
        return this.pagamentoRepository.atualizarStatusPagamento(pagamento, status);
    }

}
