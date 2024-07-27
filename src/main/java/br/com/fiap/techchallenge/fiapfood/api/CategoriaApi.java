package br.com.fiap.techchallenge.fiapfood.api;


import br.com.fiap.techchallenge.fiapfood.__adapters.CategoriaResponse;
import br.com.fiap.techchallenge.fiapfood.adapters.controllers.CategoriaController;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.CategoriaJsonPresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Categoria API")
@RestController
@RequestMapping("/api/v1/Categoria")
public class CategoriaApi {

    private CategoriaController categoriaController;

    public CategoriaApi(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    @Operation(summary = "Buscar todas as categorias", description = "Buscar todas as categorias")
    @GetMapping("/buscarTodasCategorias")
    public List<CategoriaResponse> buscarTodasCategorias(){
        return categoriaController.buscarTodasCategorias(new CategoriaJsonPresenter());
    }

}
