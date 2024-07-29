package br.com.fiap.techchallenge.fiapfood.core.usecases;

import br.com.fiap.techchallenge.fiapfood.dto.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.List;

public interface CategoriaUseCaseBoundary {

    List<CategoriaResponse> buscarTodasCategorias() throws FiapFoodException;
}
