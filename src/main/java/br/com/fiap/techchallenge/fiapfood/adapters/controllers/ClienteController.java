package br.com.fiap.techchallenge.fiapfood.adapters.controllers;

import br.com.fiap.techchallenge.fiapfood.dto.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.dto.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ClientePresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ClienteUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private ClienteUseCaseBoundary clienteUseCaseBoundary;
    private ClientePresenter clientePresenter;


    public ClienteController(ClienteUseCaseBoundary clienteUseCaseBoundary) {
        this.clienteUseCaseBoundary = clienteUseCaseBoundary;
    }

    public List<ClienteResponse> buscarTodosClientes(ClientePresenter clientePresenter) {

        List<ClienteResponse> list = new ArrayList<>();
        try {
            list = this.clienteUseCaseBoundary.buscarTodosClientes();
            return clientePresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaListaSemSucesso(err);
        }
    }

    public ClienteResponse inserir(ClienteRequest clienteRequest, ClientePresenter clientePresenter) {

        try {

            ClienteResponse savedCliente = this.clienteUseCaseBoundary.inserirCliente(clienteRequest);

            if (savedCliente != null)
                return clientePresenter.prepararRespostaComSucesso(savedCliente);
            else
                return clientePresenter.prepararRespostaVazia();
        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaErro(err);
        }
    }

    public ClienteResponse buscarClientePorCpf(String cpf, ClientePresenter clientePresenter) {

        try {
            ClienteResponse cliente = this.clienteUseCaseBoundary.buscarClientePorCpf(new Cpf(cpf));
            if (cliente != null)
                return clientePresenter.prepararRespostaComSucesso(cliente);
            else
                return clientePresenter.prepararRespostaVazia();

        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaErro(err);
        }
    }

    public ClienteResponse alterar(ClienteRequest clienteRequest, ClientePresenter clientePresenter) {

        try {

            ClienteResponse clienteResponse = this.clienteUseCaseBoundary.atualizar(clienteRequest);

            if (clienteResponse != null) {
                return clientePresenter.prepararRespostaComSucesso(clienteResponse);
            } else {
                return clientePresenter.prepararRespostaVazia();
            }
        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaErro(err);
        }
    }

    public Boolean excluir(String cpf, ClientePresenter clientePresenter) {

        try {
            return this.clienteUseCaseBoundary.excluir(new Cpf(cpf));
        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaComErroExcluir(err);
        }
    }


}
