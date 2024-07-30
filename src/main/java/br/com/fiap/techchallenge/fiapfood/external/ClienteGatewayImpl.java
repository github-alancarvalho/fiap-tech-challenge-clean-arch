package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.external.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.external.entities.ClienteEntity;

import java.util.List;


public class ClienteGatewayImpl extends GenericDaoImpl<ClienteEntity> implements ClienteGateway {

    public ClienteGatewayImpl(){
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
//        ClienteEntity entity = find(cpf.getCpfSomenteNumero());
//        if (entity != null){
//            delete(entity);
            deleteById(Long.valueOf(cpf.getCpfSomenteNumero()));
            return true;
//        } else {
//            return false;
//        }
    }

    @Override
    public List<Cliente> listarTudo() {
        return ClienteMapper.mapListToEntity(findAll());
    }
}

