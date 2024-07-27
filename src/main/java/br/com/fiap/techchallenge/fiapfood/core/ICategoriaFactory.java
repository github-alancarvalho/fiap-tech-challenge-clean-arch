package br.com.fiap.techchallenge.fiapfood.core;

public interface ICategoriaFactory {
    ICategoria create(Long id, String nome, String descricao);
}
