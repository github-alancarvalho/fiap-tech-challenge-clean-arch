package br.com.fiap.techchallenge.fiapfood.__adapters;

import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoResponse {

    @NotEmpty(message = "Id não pode ser vazio")
    @NotNull(message = "Id não pode ser nulo")
    @Schema(description = "Código do produto,", example = "123")
    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    @Schema(description = "Nome do produto,", example = "Sanduba do Zé")
    private String nome;

    @NotEmpty(message = "Descricao não pode ser vazio")
    @NotNull(message = "Descricao não pode ser nulo")
    @Schema(description = "Descricao do produto,", example = "Sandubão com ovo e bacon")
    private String descricao;

    @NotEmpty(message = "Categoria não pode ser vazia")
    @NotNull(message = "Categoria não pode ser nula")
    @Schema(description = "Categoria do produto,")
    private Categoria categoria;

    @NotEmpty(message = "Preço não pode ser vazio")
    @NotNull(message = "Preço não pode ser nulo")
    @Schema(description = "Preço do produto,", example = "10.48")
    private Double preco;

    public ProdutoResponse() {
    }

    public ProdutoResponse(Long id, String nome, String descricao, Categoria categoria, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
