package br.com.fiap.techchallenge.fiapfood.adapters.presenters;

import br.com.fiap.techchallenge.fiapfood.dto.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ProdutoDefaultPresenter implements ProdutoPresenter{
    @Override
    public ProdutoResponse prepararRespostaComSucesso(ProdutoResponse produtoResponse) {
        return produtoResponse;
    }

    @Override
    public List<ProdutoResponse> prepararRespostaListaComSucesso(List<ProdutoResponse> listProdutoResponse) {
        return listProdutoResponse;
    }

    @Override
    public ProdutoResponse prepararRespostaVazia() {
        return null;
    }

    @Override
    public Boolean prepararRespostaComErroExcluir(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public ProdutoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public List<ProdutoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }
}
