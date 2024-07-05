package br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;


public class ExcluirClienteUseCase {

    private ClienteRepository clienteRepository;

    public ExcluirClienteUseCase() {
        this.clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
    }

    public Boolean excluir(Cpf cpf) {
        return this.clienteRepository.excluir(cpf);
    }


}
