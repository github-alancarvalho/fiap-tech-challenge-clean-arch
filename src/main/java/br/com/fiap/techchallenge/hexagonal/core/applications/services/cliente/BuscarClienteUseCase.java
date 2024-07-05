package br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;


public class BuscarClienteUseCase {

    private ClienteRepository clienteRepository;


    public BuscarClienteUseCase() {
        this.clienteRepository = DaoFactory.getInstance().getClienteRepositoryORM();
    }

    public Optional<Cliente> buscarClientePorCpfORM(Cpf cpf) {
        return this.clienteRepository.buscarPorCpf(cpf);
    }

    public Optional<List<Cliente>> buscarTodosClientes() {
        return this.clienteRepository.listarTudo();
    }

}
