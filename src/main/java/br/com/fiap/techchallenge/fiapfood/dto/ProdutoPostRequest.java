package br.com.fiap.techchallenge.fiapfood.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoPostRequest {

    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    @Schema(description = "Nome do produto,", example = "Sanduba do Zé")
    private String nome;

    @NotEmpty(message = "Descricao não pode ser vazio")
    @NotNull(message = "Descricao não pode ser nulo")
    @Schema(description = "Descricao do produto,", example = "Sandubão com ovo e bacon")
    private String descricao;

    @NotEmpty(message = "CategoriaEntity não pode ser vazia")
    @NotNull(message = "CategoriaEntity não pode ser nula")
    @Schema(description = "CategoriaEntity do produto.(1 - Lanche, 2 - Acompanhamento, 3 - Sobremesa, 4 - Bebida)", example = "1")
    private Long categoriaId;

    @NotEmpty(message = "Preço não pode ser vazio")
    @NotNull(message = "Preço não pode ser nulo")
    @Schema(description = "Preço do produto,", example = "10.48")
    private Double preco;

    public ProdutoPostRequest() {
    }

    public ProdutoPostRequest(String nome, String descricao, Long categoriaId, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.preco = preco;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
