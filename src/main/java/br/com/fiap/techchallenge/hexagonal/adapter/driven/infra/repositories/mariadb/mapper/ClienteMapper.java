package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ClienteORM;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Cliente mapToEntity(ClienteORM entity) {
        if (entity == null) {
            return null;
        }
        return new Cliente(
                new Cpf(entity.getCpf()),
                entity.getNome(),
                entity.getEmail(),
                new Telefone(entity.getTelefone())
        );
    }

    public static ClienteORM mapToEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteORM(
                cliente.getCpf() == null ? null : cliente.getCpf().getCpfSomenteNumero(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone() == null ? null : cliente.getTelefone().getTelefone()
        );
    }

    public static List<Cliente> mapListToEntity(List<ClienteORM> listEntity) {
        List<Cliente> list = new ArrayList<>();
        for ( ClienteORM clienteORM : listEntity ){
            list.add(Cliente.builder()
                    .nome(clienteORM.getNome())
                    .cpf(new Cpf(clienteORM.getCpf()))
                            .telefone(new Telefone(clienteORM.getTelefone()))
                            .email(clienteORM.getEmail()).build()
                    );
        }
        return list;
    }


}