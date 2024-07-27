package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.external.entities.ClienteEntity;
import br.com.fiap.techchallenge.fiapfood.core.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Telefone;

import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {

    private ClienteMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Cliente mapToEntity(ClienteEntity entity) {
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

    public static ClienteEntity mapToEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return new ClienteEntity(
                cliente.getCpf() == null ? null : cliente.getCpf().getCpfSomenteNumero(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone() == null ? null : cliente.getTelefone().getTelefone()
        );
    }

    public static List<Cliente> mapListToEntity(List<ClienteEntity> listEntity) {
        List<Cliente> list = new ArrayList<>();
        for ( ClienteEntity clienteEntity : listEntity ){
            list.add(Cliente.builder()
                    .nome(clienteEntity.getNome())
                    .cpf(new Cpf(clienteEntity.getCpf()))
                            .telefone(new Telefone(clienteEntity.getTelefone()))
                            .email(clienteEntity.getEmail()).build()
                    );
        }
        return list;
    }


}