package br.com.fiap.techchallenge.fiapfood.adapters.controllers;

import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ProdutoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ProdutoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private ProdutoUseCaseBoundary produtoUseCaseBoundary;
    private ProdutoPresenter produtoPresenter;


    public ProdutoController(ProdutoUseCaseBoundary produtoUseCaseBoundary) {
        this.produtoUseCaseBoundary = produtoUseCaseBoundary;
    }

    public List<ProdutoResponse> buscarTodosProdutos(ProdutoPresenter produtoPresenter) {

        List<ProdutoResponse> list = new ArrayList<>();
        try {
            list = this.produtoUseCaseBoundary.buscarTodosProdutos();
            return produtoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

    public ProdutoResponse inserir(ProdutoPostRequest produtoRequest, ProdutoPresenter produtoPresenter) {

        try {

            ProdutoResponse savedProduto = this.produtoUseCaseBoundary.inserirProduto(produtoRequest);

            if (savedProduto != null)
                return produtoPresenter.prepararRespostaComSucesso(savedProduto);
            else
                return produtoPresenter.prepararRespostaVazia();
        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public ProdutoResponse buscarProdutoPorId(Long id, ProdutoPresenter produtoPresenter) {

        try {
            ProdutoResponse produto = this.produtoUseCaseBoundary.buscarProdutoPorId(id);
            if (produto != null)
                return produtoPresenter.prepararRespostaComSucesso(produto);
            else
                return produtoPresenter.prepararRespostaVazia();

        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public ProdutoResponse alterar(ProdutoPutRequest produtoRequest, ProdutoPresenter produtoPresenter) {

        try {

            ProdutoResponse produtoResponse = this.produtoUseCaseBoundary.atualizar(produtoRequest);

            if (produtoResponse != null) {
                return produtoPresenter.prepararRespostaComSucesso(produtoResponse);
            } else {
                return produtoPresenter.prepararRespostaVazia();
            }
        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public Boolean excluir(Long id, ProdutoPresenter produtoPresenter) {

        try {
            return this.produtoUseCaseBoundary.excluir(id);
        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaComErroExcluir(err);
        }
    }

    public List<ProdutoResponse> buscarProdutosPorCategoria(Long categoriaId, ProdutoPresenter produtoPresenter) {

        List<ProdutoResponse> list = new ArrayList<>();
        try {
            list = this.produtoUseCaseBoundary.buscarProdutosPorCategoria(Categoria.builder().id(categoriaId).build());
            return produtoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return produtoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

}
