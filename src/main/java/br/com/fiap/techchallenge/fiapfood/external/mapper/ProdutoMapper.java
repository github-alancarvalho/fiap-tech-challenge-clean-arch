package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;
import br.com.fiap.techchallenge.fiapfood.external.entities.ProdutoEntity;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    private ProdutoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Produto mapToEntity(ProdutoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Produto(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                CategoriaMapper.mapToEntity(entity.getCategoriaEntity()),
                entity.getPreco()
        );
    }

    public static ProdutoEntity mapToEntity(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoEntity(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                CategoriaMapper.mapToEntity(produto.getCategoria()),
                produto.getPreco()
        );
    }

    public static List<Produto> mapListToEntity(List<ProdutoEntity> listEntity) {
        List<Produto> list = new ArrayList<>();
        for (ProdutoEntity produtoEntity : listEntity) {
            list.add(Produto.builder()
                    .id(produtoEntity.getId())
                    .nome(produtoEntity.getNome())
                    .descricao(produtoEntity.getDescricao())
                    .categoria(CategoriaMapper.mapToEntity(produtoEntity.getCategoriaEntity()))
                            .preco(produtoEntity.getPreco()).build()
            );
        }
        return list;
    }
}