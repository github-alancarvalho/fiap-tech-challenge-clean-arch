package br.com.fiap.techchallenge.fiapfood.__usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.List;

public interface CategoriaUseCaseBoundary {

    List<CategoriaResponse> buscarTodasCategorias() throws FiapFoodException;
}
