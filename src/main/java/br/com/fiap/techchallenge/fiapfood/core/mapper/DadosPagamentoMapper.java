package br.com.fiap.techchallenge.fiapfood.core.mapper;

import br.com.fiap.techchallenge.fiapfood.dto.DadosPagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.core.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;

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