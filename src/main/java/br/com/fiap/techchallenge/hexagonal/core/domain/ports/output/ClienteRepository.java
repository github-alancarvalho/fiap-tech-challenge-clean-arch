package br.com.fiap.techchallenge.hexagonal.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Optional<Cliente> inserirClienteORM(Cliente cliente);

    Optional<Cliente> buscarPorCpf(Cpf cpf);

    Optional<Cliente> atualizar(Cliente cliente);

    Boolean excluir(Cpf cpf);

    Optional<List<Cliente>> listarTudo();
}