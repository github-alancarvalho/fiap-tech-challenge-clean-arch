package br.com.fiap.techchallenge.fiapfood.__presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.ArrayList;
import java.util.List;

public class PagamentoJsonPresenter implements PagamentoPresenter{
    @Override
    public PagamentoResponse prepararRespostaComSucesso(PagamentoResponse pagamentoResponse) {
        return pagamentoResponse;
    }

    @Override
    public List<PagamentoResponse> prepararRespostaListaComSucesso(List<PagamentoResponse> listPagamentoResponse) {
        return listPagamentoResponse;
    }

    @Override
    public PagamentoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        return PagamentoResponse.builder().build();
    }

    @Override
    public List<PagamentoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        return new ArrayList<PagamentoResponse>();
    }
}
