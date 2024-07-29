package br.com.fiap.techchallenge.fiapfood.adapters.presenters;


import br.com.fiap.techchallenge.fiapfood.dto.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.List;


public interface ProdutoPresenter {

    ProdutoResponse prepararRespostaComSucesso(ProdutoResponse produtoResponse);
    List<ProdutoResponse> prepararRespostaListaComSucesso(List<ProdutoResponse> listProdutoResponse);

    ProdutoResponse prepararRespostaVazia();

    Boolean prepararRespostaComErroExcluir(FiapFoodException err);

    ProdutoResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<ProdutoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}