package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood._infra.persistence.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ClienteORM;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper.ClienteMapper;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class ClienteDao extends ConnectionPoolManager implements ClienteRepository {


    private EntityManager entityManager;

    public ClienteDao(){
        this.entityManager = getConnection().createEntityManager();
    }


    public Optional<Cliente> inserirClienteORM(Cliente cliente) {

        entityManager.getTransaction().begin();
        ClienteORM entity = ClienteMapper.mapToEntity(cliente);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));

    }

    public Optional<Cliente> buscarPorCpf(Cpf cpf) {
        ClienteORM entity = entityManager.find(ClienteORM.class, cpf.getCpfSomenteNumero());
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));
    }


    public Optional<Cliente> atualizar(Cliente cliente) {
        entityManager.getTransaction().begin();
        ClienteORM entity = ClienteMapper.mapToEntity(cliente);
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        return Optional.ofNullable(ClienteMapper.mapToEntity(entity));

    }


    public Boolean excluir(Cpf cpf) {
        ClienteORM entity = entityManager.find(ClienteORM.class, cpf.getCpfSomenteNumero());
        if (entity != null){
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            return true;
        } else {
            return false;
        }
    }


    public Optional<List<Cliente>> listarTudo() {
        Query query = entityManager.createNamedQuery("findAllClientes");
        List<ClienteORM> list = query.getResultList();
        return Optional.ofNullable(ClienteMapper.mapListToEntity(list));
    }
}

