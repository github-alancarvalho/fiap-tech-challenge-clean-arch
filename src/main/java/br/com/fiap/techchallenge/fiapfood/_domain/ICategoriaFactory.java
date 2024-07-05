package br.com.fiap.techchallenge.fiapfood._domain;

public interface ICategoriaFactory {
    ICategoria create(Long id, String nome, String descricao);
}
