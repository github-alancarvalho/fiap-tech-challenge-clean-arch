package br.com.fiap.techchallenge.fiapfood.external.mapper;

import br.com.fiap.techchallenge.fiapfood.external.entities.CategoriaEntity;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;

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