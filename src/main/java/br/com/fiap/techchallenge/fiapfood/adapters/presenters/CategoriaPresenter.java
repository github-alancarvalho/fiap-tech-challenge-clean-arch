package br.com.fiap.techchallenge.fiapfood.adapters.presenters;


import br.com.fiap.techchallenge.fiapfood.dto.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.List;


public interface CategoriaPresenter {

    CategoriaResponse prepararRespostaComSucesso(CategoriaResponse categoriaResponse);
    List<CategoriaResponse> prepararRespostaListaComSucesso(List<CategoriaResponse> listCategoriaResponse);
    CategoriaResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<CategoriaResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
