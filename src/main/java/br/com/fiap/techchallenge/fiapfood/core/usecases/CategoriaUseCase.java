package br.com.fiap.techchallenge.fiapfood.core.usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;

import java.util.List;
import java.util.stream.Collectors;


public class CategoriaUseCase implements CategoriaUseCaseBoundary {

    private final CategoriaGateway categoriaGateway;

    public CategoriaUseCase(CategoriaGateway categoriaGateway) {
        this.categoriaGateway = categoriaGateway;
    }

    public List<CategoriaResponse> buscarTodasCategorias() throws FiapFoodException {

        List<Categoria> list = this.categoriaGateway.listarCategorias();
        return list.stream().map(c -> new CategoriaResponse(c.getId(), c.getNome(), c.getDescricao())).collect(Collectors.toList());
    }

}
