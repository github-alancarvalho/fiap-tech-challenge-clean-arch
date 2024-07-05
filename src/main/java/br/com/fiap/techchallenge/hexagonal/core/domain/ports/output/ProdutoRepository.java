package br.com.fiap.techchallenge.hexagonal.core.domain.ports.output;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {

    Optional<Produto> inserir(Produto produto);

    Optional<Produto> buscarPorId(Long id);

    Optional<List<Produto>> listarPorCategoria(Categoria categoria);

    Optional<List<Produto>> listarTudo();

    Boolean excluir(Produto produto);

    Optional<Produto> atualizar(Produto produto);
}