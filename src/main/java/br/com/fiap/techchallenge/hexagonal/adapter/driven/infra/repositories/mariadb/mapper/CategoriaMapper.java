package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.mapper;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.__db.CategoriaDataMapper;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMapper {

    private CategoriaMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Categoria mapToEntity(CategoriaDataMapper entity) {
        if (entity == null) {
            return null;
        }
        return new Categoria(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao()
        );
    }

    public static CategoriaDataMapper mapToEntity(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return new CategoriaDataMapper(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }

    public static List<Categoria> mapListToEntity(List<CategoriaDataMapper> listEntity) {
        List<Categoria> list = new ArrayList<>();
        for ( CategoriaDataMapper categoriaDataMapper : listEntity ){
            list.add(Categoria.builder()
                            .id(categoriaDataMapper.getId())
                    .nome(categoriaDataMapper.getNome())
                            .descricao(categoriaDataMapper.getDescricao()).build()
                    );
        }
        return list;
    }


}