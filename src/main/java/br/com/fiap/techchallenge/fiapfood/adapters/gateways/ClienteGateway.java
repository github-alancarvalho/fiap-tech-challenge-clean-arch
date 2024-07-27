package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.core.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;

import java.util.List;

public interface ClienteGateway {

    Cliente inserirCliente(Cliente cliente);

    Cliente buscarPorCpf(Cpf cpf);

    Cliente atualizar(Cliente cliente);

    Boolean excluir(Cpf cpf);

    List<Cliente> listarTudo();
}