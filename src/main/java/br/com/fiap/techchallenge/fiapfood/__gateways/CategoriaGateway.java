package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;

import java.util.List;

public interface CategoriaGateway {

    List<Categoria> listarCategorias();
}
