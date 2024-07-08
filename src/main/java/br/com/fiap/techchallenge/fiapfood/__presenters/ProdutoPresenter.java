package br.com.fiap.techchallenge.fiapfood.__presenters;


import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.List;


public interface ProdutoPresenter {

    ProdutoResponse prepararRespostaComSucesso(ProdutoResponse produtoResponse);
    List<ProdutoResponse> prepararRespostaListaComSucesso(List<ProdutoResponse> listProdutoResponse);
    ProdutoResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<ProdutoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
