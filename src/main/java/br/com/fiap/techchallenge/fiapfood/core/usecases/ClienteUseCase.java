package br.com.fiap.techchallenge.fiapfood.core.usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Telefone;

import java.util.List;
import java.util.stream.Collectors;


public class ClienteUseCase implements ClienteUseCaseBoundary {

    private ClienteGateway clienteGateway;

    public ClienteUseCase(ClienteGateway clienteGateway) {

        this.clienteGateway = clienteGateway;
    }

    public ClienteResponse inserirCliente(ClienteRequest clienteRequest) throws FiapFoodException {
        try {
            Cliente cliente = Cliente.builder().cpf(new Cpf(clienteRequest.getCpf()))
                    .nome(clienteRequest.getNome())
                    .email(clienteRequest.getEmail())
                    .telefone(new Telefone(clienteRequest.getTelefone()))
                    .build();

            Cliente clienteSaved = this.clienteGateway.inserirCliente(cliente);

            return ClienteResponse.builder().cpf(clienteSaved.getCpf().getCpfSomenteNumero())
                    .nome(clienteSaved.getNome())
                    .email(clienteSaved.getEmail())
                    .telefone(clienteSaved.getTelefone().getTelefone())
                    .build();

        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public ClienteResponse buscarClientePorCpf(Cpf cpf) throws FiapFoodException {
        try {

            Cliente cliente = this.clienteGateway.buscarPorCpf(cpf);

            if (cliente != null)
                return ClienteResponse.builder().cpf(cliente.getCpf().getCpfSomenteNumero())
                        .nome(cliente.getNome())
                        .email(cliente.getEmail())
                        .telefone(cliente.getTelefone().getTelefone())
                        .build();
            else
                return null;

        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public List<ClienteResponse> buscarTodosClientes() throws FiapFoodException {
        try {
            List<Cliente> list = this.clienteGateway.listarTudo();
            return list.stream().map(c -> new ClienteResponse(c.getCpf().getCpfSomenteNumero(), c.getNome(), c.getEmail(), c.getTelefone().getTelefone())).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public ClienteResponse atualizar(ClienteRequest clienteRequest) throws FiapFoodException {
        try {
            Cliente cliente = Cliente.builder().cpf(new Cpf(clienteRequest.getCpf()))
                    .nome(clienteRequest.getNome())
                    .email(clienteRequest.getEmail())
                    .telefone(new Telefone(clienteRequest.getTelefone()))
                    .build();

            Cliente clienteSaved = this.clienteGateway.atualizar(cliente);

            return ClienteResponse.builder().cpf(clienteSaved.getCpf().getCpfSomenteNumero())
                    .nome(clienteSaved.getNome())
                    .email(clienteSaved.getEmail())
                    .telefone(clienteSaved.getTelefone().getTelefone())
                    .build();

        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public Boolean excluir(Cpf cpf) throws FiapFoodException {
        try {
            return this.clienteGateway.excluir(cpf);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }
}
