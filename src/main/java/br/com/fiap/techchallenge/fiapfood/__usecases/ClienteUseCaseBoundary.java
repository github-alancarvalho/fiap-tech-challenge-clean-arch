package br.com.fiap.techchallenge.fiapfood.__usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;

import java.util.List;

public interface ClienteUseCaseBoundary {

    public ClienteResponse inserirCliente(ClienteRequest clienteRequest) throws FiapFoodException;

    public ClienteResponse buscarClientePorCpf(Cpf cpf) throws FiapFoodException;

    public List<ClienteResponse> buscarTodosClientes() throws FiapFoodException;

    public ClienteResponse atualizar(ClienteRequest clienteRequest) throws FiapFoodException;

    public Boolean excluir(Cpf cpf) throws FiapFoodException;

}
