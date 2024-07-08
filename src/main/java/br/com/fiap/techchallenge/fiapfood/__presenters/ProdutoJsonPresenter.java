package br.com.fiap.techchallenge.fiapfood.__presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ProdutoJsonPresenter implements ProdutoPresenter{
    @Override
    public ProdutoResponse prepararRespostaComSucesso(ProdutoResponse produtoResponse) {
        return produtoResponse;
    }

    @Override
    public List<ProdutoResponse> prepararRespostaListaComSucesso(List<ProdutoResponse> listProdutoResponse) {
        return listProdutoResponse;
    }

    @Override
    public ProdutoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        return new ProdutoResponse(null, null, null, null, null);
    }

    @Override
    public List<ProdutoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        return new ArrayList<ProdutoResponse>();
    }
}
