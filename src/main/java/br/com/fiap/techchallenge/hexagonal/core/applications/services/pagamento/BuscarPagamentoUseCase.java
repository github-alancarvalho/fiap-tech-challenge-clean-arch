package br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PagamentoRepository;

import java.util.List;
import java.util.Optional;


public class BuscarPagamentoUseCase {

    private PagamentoRepository pagamentoRepository;

    public BuscarPagamentoUseCase() {
        this.pagamentoRepository = DaoFactory.getInstance().getPagamentoRepositoryORM();
    }

    public Optional<Pagamento> buscarPagamentoPorId(Long id) {
        return this.pagamentoRepository.buscarPagamentoPorId(id);
    }

    public Optional<List<Pagamento>> buscarTodosPagamentos() {
        return this.pagamentoRepository.listarPagamentos();
    }

}
