package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.ICategoria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Categoria implements ICategoria {

    private static final int MINIMUM_CHARACTER_LIMIT = 3;

    private Long id;
    private String nome;
    private String descricao;


    public Categoria(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public Boolean isNomeValido() {
        return nome != null && nome.length() >= MINIMUM_CHARACTER_LIMIT;
    }

    @Override
    public Boolean isDescricaoValida() {
        return descricao != null && descricao.length() >= MINIMUM_CHARACTER_LIMIT;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}