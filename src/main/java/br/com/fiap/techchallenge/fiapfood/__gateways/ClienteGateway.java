package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;

import java.util.List;

public interface ClienteGateway {

    Cliente inserirCliente(Cliente cliente);

    Cliente buscarPorCpf(Cpf cpf);

    Cliente atualizar(Cliente cliente);

    Boolean excluir(Cpf cpf);

    List<Cliente> listarTudo();
}