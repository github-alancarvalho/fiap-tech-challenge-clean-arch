package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb;

import br.com.fiap.techchallenge.fiapfood.__db.ConnectionPoolManager;
import br.com.fiap.techchallenge.fiapfood.__db.PagamentoEntity;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.PagamentoMapper;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;


public class PagamentoDao extends ConnectionPoolManager implements PagamentoGateway {

    private EntityManager entityManager;

    @Override
    public Optional<Pagamento> processarPagamento(Pagamento pagamento) {
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        PagamentoEntity entity = PagamentoMapper.mapToEntity(pagamento);
        entityManager.persist(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<Pagamento> atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status) {
        PagamentoEntity entity = PagamentoMapper.mapToEntity(buscarPagamentoPorId(pagamento.getId()).get());
        entity.setStatus(status);
        entityManager = getConnection().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<Pagamento> buscarPagamentoPorId(Long id) {
        entityManager = getConnection().createEntityManager();
        PagamentoEntity entity = entityManager.find(PagamentoEntity.class, id);
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapToEntity(entity));
    }

    @Override
    public Optional<List<Pagamento>> listarPagamentos() {
        entityManager = getConnection().createEntityManager();
        Query query = entityManager.createNamedQuery("findAllPagamentos");
        List<PagamentoEntity> list = query.getResultList();
        entityManager.close();
        return Optional.ofNullable(PagamentoMapper.mapListToEntity(list));
    }
}

