package br.com.fiap.techchallenge.hexagonal.adapter.driver.controllers;

import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.ProdutoPostRequest;
import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.ProdutoPutRequest;
import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.ProdutoResponse;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.produto.AtualizarProdutoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.produto.BuscarProdutoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.produto.ExcluirProdutoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.produto.InserirProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "ProdutoORM API")
@RestController
@RequestMapping("/api/v1/ProdutosORM")
public class ProdutoController {

    private final InserirProdutoUseCase inserirProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final ExcluirProdutoUseCase excluirProdutoUseCase;

    public ProdutoController() {

        this.inserirProdutoUseCase = new InserirProdutoUseCase();
        this.buscarProdutoUseCase = new BuscarProdutoUseCase();
        this.atualizarProdutoUseCase = new AtualizarProdutoUseCase();
        this.excluirProdutoUseCase = new ExcluirProdutoUseCase();
    }

    @Operation(summary = "Inserir ProdutoORM", description = "Inserir novo ProdutoORM")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ProdutoResponse>> inserir(@Valid @RequestBody ProdutoPostRequest produtoRequest) {

        Produto produto = Produto.builder()
                .nome(produtoRequest.getNome())
                .descricao(produtoRequest.getDescricao())
                .preco(produtoRequest.getPreco())
                .categoria(Categoria.builder().id(produtoRequest.getCategoriaId()).build())
                .build();

        Optional<Produto> savedProduto = inserirProdutoUseCase.inserir(produto);
        if (!savedProduto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(savedProduto.get().getId())
                    .nome(savedProduto.get().getNome())
                    .descricao(savedProduto.get().getDescricao())
                    .preco(savedProduto.get().getPreco())
                    .categoria(savedProduto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar ProdutoORM por Id", description = "Buscar ProdutoORM por Id")
    @GetMapping("/buscarProdutoPorId")
    public ResponseEntity<Optional<ProdutoResponse>> buscarProdutoPorId(@RequestParam("id") Long id) {

        Optional<Produto> produto = buscarProdutoUseCase.buscarProdutoPorId(id);
        if (!produto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(produto.get().getId())
                    .nome(produto.get().getNome())
                    .descricao(produto.get().getDescricao())
                    .preco(produto.get().getPreco())
                    .categoria(produto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar produto", description = "Alterar produto. Id é mandatório")
    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ProdutoResponse>> alterar(@Valid @RequestBody ProdutoPutRequest produtoRequest) {
        Produto produto = Produto.builder()
                .id(produtoRequest.getId())
                .nome(produtoRequest.getNome())
                .descricao(produtoRequest.getDescricao())
                .preco(produtoRequest.getPreco())
                .categoria(Categoria.builder().id(produtoRequest.getCategoriaId()).build())
                .build();

        Optional<Produto> savedProduto = atualizarProdutoUseCase.atualizar(produto);

        if (!savedProduto.isEmpty()) {
            ProdutoResponse response = ProdutoResponse.builder()
                    .id(savedProduto.get().getId())
                    .nome(savedProduto.get().getNome())
                    .descricao(savedProduto.get().getDescricao())
                    .preco(savedProduto.get().getPreco())
                    .categoria(savedProduto.get().getCategoria()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir produto por id", description = "Excluir produto por id, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        Produto produto = Produto.builder()
                .id(id).build();

        Boolean isExcluded = excluirProdutoUseCase.excluir(produto);
        if (Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os produtos", description = "Buscar todos os produtos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarTudo() {
        Optional<List<Produto>> produtos = buscarProdutoUseCase.buscarTodosProdutos();
        if (!produtos.isEmpty()) {

            List<ProdutoResponse> list = new ArrayList<>();
            for (Produto produto : produtos.get()) {
                ProdutoResponse response = ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .categoria(produto.getCategoria()).build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar produtos por categoria", description = "Buscar produtos por categoria. (1 - Lanche, 2 - Acompanhamento, 3 - Sobremesa, 4 - Bebida)")
    @GetMapping("/buscarProdutosPorCategoria")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarProdutosPorCategoria(@RequestParam("id") Long id) {
        Optional<List<Produto>> produtos = buscarProdutoUseCase.buscarProdutosPorCategoria(
                Categoria.builder().id(id).build());
        if (!produtos.isEmpty()) {

            List<ProdutoResponse> list = new ArrayList<>();
            for (Produto produto : produtos.get()) {
                ProdutoResponse response = ProdutoResponse.builder()
                        .id(produto.getId())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .categoria(produto.getCategoria()).build();
                list.add(response);
            }
            if(!list.isEmpty())
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

