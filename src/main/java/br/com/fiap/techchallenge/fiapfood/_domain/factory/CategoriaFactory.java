package br.com.fiap.techchallenge.fiapfood._domain.factory;

import br.com.fiap.techchallenge.fiapfood._domain.ICategoria;
import br.com.fiap.techchallenge.fiapfood._domain.ICategoriaFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;

public class CategoriaFactory implements ICategoriaFactory {

    @Override
    public ICategoria create(Long id, String nome, String descricao) {
        return new Categoria(id, nome, descricao);
    }
}

