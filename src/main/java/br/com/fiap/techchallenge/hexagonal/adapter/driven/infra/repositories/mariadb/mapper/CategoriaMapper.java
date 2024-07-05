package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaEntity;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    private CategoriaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Categoria mapToEntity(CategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Categoria(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static CategoriaEntity mapToEntity(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaEntity(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    public static List<Categoria> mapListToEntity(List<CategoriaEntity> listEntity) {
        List<Categoria> list = new ArrayList<>();
        for ( CategoriaEntity categoriaEntity : listEntity ){
            list.add(Categoria.builder()
                            .id(categoriaEntity.getId())
                    .nome(categoriaEntity.getNome())
                            .descricao(categoriaEntity.getDescricao()).build()
                    );
        }
        return list;
    }


}