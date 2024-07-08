package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood.__db.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;

import java.util.List;


public class ClienteGatewayDataMapper extends GenericDaoImpl<ClienteEntity> implements ClienteGateway {

    public ClienteGatewayDataMapper(){
        setClazz(ClienteEntity.class);
        setEntityManager((new ConnectionPoolManager()).getConnection().createEntityManager());
    }

    @Override
    public Cliente inserirCliente(Cliente cliente) {
        ClienteEntity entity = save(ClienteMapper.mapToEntity(cliente));
        return ClienteMapper.mapToEntity(entity);
    }

    @Override
    public Cliente buscarPorCpf(Cpf cpf) {
        ClienteEntity entity = find(cpf.getCpfSomenteNumero());
        return ClienteMapper.mapToEntity(entity);
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        ClienteEntity entity = update(ClienteMapper.mapToEntity(cliente));
        return ClienteMapper.mapToEntity(entity);
    }

    @Override
    public Boolean excluir(Cpf cpf) {
        ClienteEntity entity = find(cpf.getCpfSomenteNumero());
        if (entity != null){
            delete(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Cliente> listarTudo() {
        return ClienteMapper.mapListToEntity(findAll());
    }
}

