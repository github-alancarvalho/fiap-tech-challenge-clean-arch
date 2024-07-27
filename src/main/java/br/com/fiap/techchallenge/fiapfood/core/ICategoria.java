package br.com.fiap.techchallenge.fiapfood.core;

public interface ICategoria {
    Long getId();
    String getNome();
    String getDescricao();
    Boolean isNomeValido();
    Boolean isDescricaoValida();
}
