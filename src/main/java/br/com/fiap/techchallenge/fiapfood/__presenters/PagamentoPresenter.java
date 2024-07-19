package br.com.fiap.techchallenge.fiapfood.__presenters;


import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.List;


public interface PagamentoPresenter {

    PagamentoResponse prepararRespostaComSucesso(PagamentoResponse pagamentoResponse);
    List<PagamentoResponse> prepararRespostaListaComSucesso(List<PagamentoResponse> listPagamentoResponse);
    PagamentoResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<PagamentoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
