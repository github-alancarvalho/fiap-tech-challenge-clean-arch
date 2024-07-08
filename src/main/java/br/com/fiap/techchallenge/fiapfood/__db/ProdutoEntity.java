package br.com.fiap.techchallenge.fiapfood.__db;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Cache(usage = CacheConcurrencyStrategy.NONE)
@Entity
@Table(name = "produto")
@NamedQuery(name = "findAllProdutos", query = "SELECT p FROM ProdutoEntity p")
public class ProdutoEntity implements Serializable {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "descricao", nullable = false)
    private String descricao;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "categoria", nullable = false)
    private CategoriaEntity categoriaEntity;

    @Column(name = "preco", nullable = false)
    private Double preco;

    public ProdutoEntity() {
    }

    public ProdutoEntity(Long id, String nome, String descricao, CategoriaEntity categoriaEntity, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaEntity = categoriaEntity;
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

    public CategoriaEntity getCategoriaEntity() {
        return categoriaEntity;
    }

    public void setCategoria(CategoriaEntity categoriaORM) {
        this.categoriaEntity = categoriaORM;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}