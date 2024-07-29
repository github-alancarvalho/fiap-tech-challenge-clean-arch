package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.external.entities.PagamentoEntity;

import java.util.ArrayList;
import java.util.List;

public class PagamentoMapper {

    private PagamentoMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static Pagamento mapToEntity2(PagamentoEntity entity) {
        if (entity == null) {
            return null;
        }
        Pagamento pagamento = new Pagamento();
        return pagamento.criarPagamentoSemClienteSemDadosDeCartao(entity.getId(),
                entity.getIdPedido(),
                entity.getStatus(),
                entity.getValor());
    }

    public static PagamentoEntity mapToEntity2(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }
        if ((pagamento.getPedido() == null) || (pagamento.getPedido().getId() == null)) {
            return null;
        }
        if (pagamento.getStatus() == null) {
            return null;
        }

        return new PagamentoEntity(
                pagamento.getIdPagamento(),
                pagamento.getPedido().getId(),
                StatusPagamento.valueOf(pagamento.getStatus()),
                pagamento.getValor()
        );
    }


    public static List<Pagamento> mapListToEntity2(List<PagamentoEntity> listEntity) {
        List<Pagamento> list = new ArrayList<>();
        for (PagamentoEntity pagamentoEntity : listEntity) {
            list.add(Pagamento.builder()
                    .idPagamento(pagamentoEntity.getId())
                    .pedido(Pedido.builder().id(pagamentoEntity.getIdPedido()).build())
                    .status(pagamentoEntity.getStatus().toString())
                    .valor(pagamentoEntity.getValor())
                    .build()
            );
        }
        return list;
    }


}