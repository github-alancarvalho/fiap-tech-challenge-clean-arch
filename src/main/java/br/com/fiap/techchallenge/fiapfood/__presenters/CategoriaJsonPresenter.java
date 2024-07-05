package br.com.fiap.techchallenge.fiapfood.__presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.ArrayList;
import java.util.List;

public class CategoriaJsonPresenter implements CategoriaPresenter{
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
