package br.com.fiap.techchallenge.fiapfood.core.usecases;

import br.com.fiap.techchallenge.fiapfood.dto.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.dto.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;

import java.util.List;

public interface ClienteUseCaseBoundary {

    public ClienteResponse inserirCliente(ClienteRequest clienteRequest) throws FiapFoodException;

    public ClienteResponse buscarClientePorCpf(Cpf cpf) throws FiapFoodException;

    public List<ClienteResponse> buscarTodosClientes() throws FiapFoodException;

    public ClienteResponse atualizar(ClienteRequest clienteRequest) throws FiapFoodException;

    public Boolean excluir(Cpf cpf) throws FiapFoodException;

}
