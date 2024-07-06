package br.com.fiap.techchallenge.fiapfood.__db;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@Entity
@Table(name = "categoria")
@NamedQuery(name = "findAllCategorias", query = "SELECT c FROM CategoriaDataMapper c")
public class CategoriaDataMapper implements Serializable {

    @NotNull
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "descricao", nullable = true)
    private String descricao;

    public CategoriaDataMapper() {
    }

    public CategoriaDataMapper(Long id, String nome, String descricao) {
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

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}