package br.com.fiap.techchallenge.fiapfood.adapters.presenters;

import br.com.fiap.techchallenge.fiapfood.dto.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDefaultPresenter implements CategoriaPresenter{
    @Override
    public CategoriaResponse prepararRespostaComSucesso(CategoriaResponse categoriaResponse) {
        return categoriaResponse;
    }

    @Override
    public List<CategoriaResponse> prepararRespostaListaComSucesso(List<CategoriaResponse> listCategoriaResponse) {
        return listCategoriaResponse;
    }

    @Override
    public CategoriaResponse prepararRespostaSemSucesso(FiapFoodException err) {
        return new CategoriaResponse(null, null, null);
    }

    @Override
    public List<CategoriaResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        return new ArrayList<CategoriaResponse>();
    }
}
