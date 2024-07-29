package br.com.fiap.techchallenge.fiapfood.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaResponse {

    @NotEmpty(message = "Id não pode ser vazio")
    @NotNull(message = "Id não pode ser nulo")
    @Schema(description = "Id do PagamentoEntity,", example = "123")
    private Long id;

    @NotEmpty(message = "Nome da Categoria não pode ser vazio")
    @NotNull(message = "Nome da Categoria não pode ser nulo")
    @Schema(description = "Nome da categoria do produto,", example = "LANCHE, SOBREMESA, ACOMPANHAMENTO, BEBIDA")
    @Email
    private String nome;

    @NotEmpty(message = "Descricao da Categoria não pode ser vazia")
    @NotNull(message = "Descricao da Categoria não pode ser nula")
    @Schema(description = "Descricao da categoria do produto,", example = "Sanduiche, beirute")
    @Email
    private String descricao;


    public CategoriaResponse(Long id, String nome, String descricao) {
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
