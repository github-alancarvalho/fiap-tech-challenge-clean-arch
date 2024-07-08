package br.com.fiap.techchallenge.fiapfood.__usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__db.ClienteEntity;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Telefone;

import java.util.List;
import java.util.stream.Collectors;


public class ClienteUseCase implements ClienteUseCaseBoundary{

    private ClienteGateway clienteGateway;

    public ClienteUseCase(ClienteGateway clienteGateway) {

        this.clienteGateway = clienteGateway;
    }

    public ClienteResponse inserirCliente(ClienteRequest clienteRequest) throws FiapFoodException {
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
    }

    public ClienteResponse buscarClientePorCpf(Cpf cpf) throws FiapFoodException {
        Cliente clienteSaved = this.clienteGateway.buscarPorCpf(cpf);

        return ClienteResponse.builder().cpf(clienteSaved.getCpf().getCpfSomenteNumero())
                .nome(clienteSaved.getNome())
                .email(clienteSaved.getEmail())
                .telefone(clienteSaved.getTelefone().getTelefone())
                .build();
    }

    public List<ClienteResponse> buscarTodosClientes() throws FiapFoodException{
        List<Cliente> list = this.clienteGateway.listarTudo();
        return list.stream().map(c -> new ClienteResponse(c.getCpf().getCpfSomenteNumero(), c.getNome(), c.getEmail(), c.getTelefone().getTelefone())).collect(Collectors.toList());
    }

    public ClienteResponse atualizar(ClienteRequest clienteRequest) throws FiapFoodException {

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

    }

    public Boolean excluir(Cpf cpf) throws FiapFoodException {
        return this.clienteGateway.excluir(cpf);
    }
}
