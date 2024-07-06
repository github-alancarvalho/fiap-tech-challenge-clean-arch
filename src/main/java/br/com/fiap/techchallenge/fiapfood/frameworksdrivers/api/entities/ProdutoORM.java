package br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities;

import br.com.fiap.techchallenge.fiapfood.__db.CategoriaDataMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Cache(usage = CacheConcurrencyStrategy.NONE)
@Entity
@Table(name = "produto")
@NamedQuery(name = "findAllProdutos", query = "SELECT p FROM ProdutoORM p")
public class ProdutoORM {

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
    @JoinColumn(name = "categoriaORM", nullable = false)
    private CategoriaDataMapper categoriaDataMapper;

    @Column(name = "preco", nullable = false)
    private Double preco;

    public ProdutoORM() {
    }

    public ProdutoORM(Long id, String nome, String descricao, CategoriaDataMapper categoriaDataMapper, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoriaDataMapper = categoriaDataMapper;
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

    public CategoriaDataMapper getCategoriaEntity() {
        return categoriaDataMapper;
    }

    public void setCategoria(CategoriaDataMapper categoriaORM) {
        this.categoriaDataMapper = categoriaORM;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}