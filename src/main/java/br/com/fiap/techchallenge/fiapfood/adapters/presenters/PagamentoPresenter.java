package br.com.fiap.techchallenge.fiapfood.adapters.presenters;


import br.com.fiap.techchallenge.fiapfood.dto.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.dto.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public interface PagamentoPresenter {

    PagamentoResponse prepararRespostaComSucesso(PagamentoResponse pagamentoResponse);
    List<PagamentoResponse> prepararRespostaListaComSucesso(List<PagamentoResponse> listPagamentoResponse);

    PagamentoResponse prepararRespostaVazia();

    String prepararResposta(PedidoResponse pedidoResponse);

    Boolean prepararRespostaComErroExcluir(FiapFoodException err);

    PagamentoResponse prepararRespostaSemSucesso(FiapFoodException err);

    String prepararRespostaSemSucesso(JsonProcessingException err);

    List<PagamentoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
