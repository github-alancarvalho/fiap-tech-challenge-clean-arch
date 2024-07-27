package br.com.fiap.techchallenge.fiapfood.core.factory;

import br.com.fiap.techchallenge.fiapfood.core.ICategoria;
import br.com.fiap.techchallenge.fiapfood.core.ICategoriaFactory;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;

public class CategoriaFactory implements ICategoriaFactory {

    @Override
    public ICategoria create(Long id, String nome, String descricao) {
        return new Categoria(id, nome, descricao);
    }
}

