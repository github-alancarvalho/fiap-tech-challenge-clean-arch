package br.com.fiap.techchallenge.fiapfood.adapters.gateways;

import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;

import java.util.List;

public interface ProdutoGateway {

    Produto inserir(Produto produto);

    Produto buscarPorId(Long id);

    List<Produto> listarPorCategoria(Categoria categoria);

    List<Produto> listarTudo();

    Boolean excluir(Long id);

    Produto atualizar(Produto produto);
}