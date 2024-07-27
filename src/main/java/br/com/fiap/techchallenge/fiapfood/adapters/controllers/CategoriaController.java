package br.com.fiap.techchallenge.fiapfood.adapters.controllers;

import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.CategoriaPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.CategoriaUseCaseBoundary;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    private CategoriaUseCaseBoundary categoriaUseCaseBoundary;
    private CategoriaPresenter categoriaPresenter;


    public CategoriaController(CategoriaUseCaseBoundary categoriaUseCaseBoundary) {
        this.categoriaUseCaseBoundary = categoriaUseCaseBoundary;
    }

    public List<CategoriaResponse> buscarTodasCategorias(CategoriaPresenter categoriaPresenter) {

        List<CategoriaResponse> list = new ArrayList<>();
        try {
                list = this.categoriaUseCaseBoundary.buscarTodasCategorias();
                return categoriaPresenter.prepararRespostaListaComSucesso(list);

        } catch(FiapFoodException err) {
            return categoriaPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

}
