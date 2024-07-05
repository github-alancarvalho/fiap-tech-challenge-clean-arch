package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.PagamentoORM;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapper {

    private PagamentoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pagamento mapToEntity(PagamentoORM entity) {
        if (entity == null) {
            return null;
        }
        return new Pagamento(
                entity.getId(),
                entity.getIdPedido(),
                entity.getStatus(),
                entity.getValor()
        );
    }

    public static PagamentoORM mapToEntity(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }
        return new PagamentoORM(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getStatus(),
                pagamento.getValor()
        );
    }

    public static List<Pagamento> mapListToEntity(List<PagamentoORM> listEntity) {
        List<Pagamento> list = new ArrayList<>();
        for (PagamentoORM pagamentoORM : listEntity) {
            list.add(Pagamento.builder()
                    .id(pagamentoORM.getId())
                    .idPedido(pagamentoORM.getIdPedido())
                    .status(pagamentoORM.getStatus())
                    .valor(pagamentoORM.getValor())
                    .build()
            );
        }
        return list;
    }


}