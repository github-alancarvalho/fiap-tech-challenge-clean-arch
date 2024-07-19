package br.com.fiap.techchallenge.fiapfood.__db.mapper;

import br.com.fiap.techchallenge.fiapfood.__adapters.DadosPagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.__db.PagamentoEntity;
import br.com.fiap.techchallenge.fiapfood._domain.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;

import java.util.ArrayList;
import java.util.List;

public class DadosPagamentoMapper {

    private DadosPagamentoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static CartaoCredito mapFromRequestToEntity(DadosPagamentoRequest pagamentoRequest) {
        if (pagamentoRequest == null) {
            return null;
        }
        return CartaoCredito.builder()
                .numero(pagamentoRequest.getNumeroCartao())
                .anoVencimento(Integer.valueOf(pagamentoRequest.getAnoVencimento()))
                .mesVencimento(Integer.valueOf(pagamentoRequest.getMesVencimento()))
                .cvv(Integer.valueOf(pagamentoRequest.getCvv()))
                .cpf(new Cpf(pagamentoRequest.getCpf())).
                build();
    }




}