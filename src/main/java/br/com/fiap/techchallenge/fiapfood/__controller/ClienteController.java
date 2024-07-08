package br.com.fiap.techchallenge.fiapfood.__controller;

import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClientePresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.ClienteUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Telefone;

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
                return clientePresenter.prepararRespostaSemSucesso(null);
        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaSemSucesso(null);
        }
    }

    public ClienteResponse buscarClientePorCpf(String cpf, ClientePresenter clientePresenter) {

        try {
            ClienteResponse cliente = this.clienteUseCaseBoundary.buscarClientePorCpf(new Cpf(cpf));
            if (cliente != null)
                return clientePresenter.prepararRespostaComSucesso(cliente);
            else
                return clientePresenter.prepararRespostaSemSucesso(null);

        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaSemSucesso(null);
        }
    }

    public ClienteResponse alterar(ClienteRequest clienteRequest, ClientePresenter clientePresenter) {

        try {

            ClienteResponse clienteResponse = this.clienteUseCaseBoundary.atualizar(clienteRequest);

            if (clienteResponse != null) {
                return clientePresenter.prepararRespostaComSucesso(clienteResponse);
            } else {
                return clientePresenter.prepararRespostaSemSucesso(null);
            }
        } catch (FiapFoodException err) {
            return clientePresenter.prepararRespostaSemSucesso(null);
        }
    }

    public Boolean excluir(String cpf) {

        try {
            return this.clienteUseCaseBoundary.excluir(new Cpf(cpf));
        } catch (FiapFoodException err) {
            return false;
        }
    }


}
