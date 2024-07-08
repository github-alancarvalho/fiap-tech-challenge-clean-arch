package br.com.fiap.techchallenge.fiapfood.__db.mapper;

import br.com.fiap.techchallenge.fiapfood.__db.PagamentoEntity;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapper {

    private PagamentoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Pagamento mapToEntity(PagamentoEntity entity) {
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

    public static PagamentoEntity mapToEntity(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }
        return new PagamentoEntity(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getStatus(),
                pagamento.getValor()
        );
    }

    public static List<Pagamento> mapListToEntity(List<PagamentoEntity> listEntity) {
        List<Pagamento> list = new ArrayList<>();
        for (PagamentoEntity pagamentoEntity : listEntity) {
            list.add(Pagamento.builder()
                    .id(pagamentoEntity.getId())
                    .idPedido(pagamentoEntity.getIdPedido())
                    .status(pagamentoEntity.getStatus())
                    .valor(pagamentoEntity.getValor())
                    .build()
            );
        }
        return list;
    }


}