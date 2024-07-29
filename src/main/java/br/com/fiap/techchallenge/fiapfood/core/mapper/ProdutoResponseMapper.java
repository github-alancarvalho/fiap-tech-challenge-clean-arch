package br.com.fiap.techchallenge.fiapfood.core.mapper;

import br.com.fiap.techchallenge.fiapfood.dto.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;

public class ProdutoResponseMapper {

    private ProdutoResponseMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Produto mapToEntity(ProdutoResponse produtoResponse) {
        if (produtoResponse == null) {
            return null;
        }
        return new Produto(
                produtoResponse.getId(),
                produtoResponse.getNome(),
                produtoResponse.getDescricao(),
                produtoResponse.getCategoria(),
                produtoResponse.getPreco()
        );
    }
}