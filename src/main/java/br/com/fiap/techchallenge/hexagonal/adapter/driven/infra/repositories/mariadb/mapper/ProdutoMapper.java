package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities.ProdutoORM;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

    private ProdutoMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Produto mapToEntity(ProdutoORM entity) {
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

    public static ProdutoORM mapToEntity(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoORM(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                CategoriaMapper.mapToEntity(produto.getCategoria()),
                produto.getPreco()
        );
    }

    public static List<Produto> mapListToEntity(List<ProdutoORM> listEntity) {
        List<Produto> list = new ArrayList<>();
        for (ProdutoORM produtoORM : listEntity) {
            list.add(Produto.builder()
                    .id(produtoORM.getId())
                    .nome(produtoORM.getNome())
                    .descricao(produtoORM.getDescricao())
                    .categoria(CategoriaMapper.mapToEntity(produtoORM.getCategoriaEntity()))
                            .preco(produtoORM.getPreco()).build()
            );
        }
        return list;
    }

}