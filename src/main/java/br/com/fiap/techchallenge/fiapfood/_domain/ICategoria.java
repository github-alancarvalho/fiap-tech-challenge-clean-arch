package br.com.fiap.techchallenge.fiapfood._domain;

public interface ICategoria {
    Long getId();
    String getNome();
    String getDescricao();
    Boolean isNomeValido();
    Boolean isDescricaoValida();
}
