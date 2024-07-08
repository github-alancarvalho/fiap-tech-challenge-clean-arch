package br.com.fiap.techchallenge.fiapfood.__api;

import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__controller.ProdutoController;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClienteJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.ProdutoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCaseBoundary;
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

@Tag(name = "ProdutoEntity API")
@RestController
@RequestMapping("/api/v1/Produtos")
public class ProdutoApi {

    private final ProdutoController produtoController;

    public ProdutoApi(ProdutoController produtoController) {
        this.produtoController = produtoController;
    }

    @Operation(summary = "Inserir ProdutoEntity", description = "Inserir novo ProdutoEntity")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ProdutoResponse>> inserir(@Valid @RequestBody ProdutoPostRequest produtoRequest) {

        ProdutoResponse savedProduto = produtoController.inserir(produtoRequest, new ProdutoJsonPresenter());

        if (savedProduto != null)
            return ResponseEntity.ok(Optional.ofNullable(savedProduto));
        else
            return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Buscar ProdutoEntity por Id", description = "Buscar ProdutoEntity por Id")
    @GetMapping("/buscarProdutoPorId")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@RequestParam("id") Long id) {

        ProdutoResponse produto =  this.produtoController.buscarProdutoPorId(id, new ProdutoJsonPresenter());
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar produto", description = "Alterar produto. Id é mandatório")
    @PutMapping("/{alterar}")
    public ResponseEntity<ProdutoResponse> alterar(@Valid @RequestBody ProdutoPutRequest produtoRequest) {

        ProdutoResponse produto = this.produtoController.alterar(produtoRequest, new ProdutoJsonPresenter());

        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir produto por id", description = "Excluir produto por id, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        Produto produto = Produto.builder()
                .id(id).build();

        Boolean isExcluded = produtoController.excluir(id);
        if (Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os produtos", description = "Buscar todos os produtos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarTudo() {
        List<ProdutoResponse> produtos = produtoController.buscarTodosProdutos(new ProdutoJsonPresenter());

        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(produtos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar produtos por categoria", description = "Buscar produtos por categoria. (1 - Lanche, 2 - Acompanhamento, 3 - Sobremesa, 4 - Bebida)")
    @GetMapping("/buscarProdutosPorCategoria")
    public ResponseEntity<Optional<List<ProdutoResponse>>> buscarProdutosPorCategoria(@RequestParam("id") Long id) {

        List<ProdutoResponse> produtos = produtoController.buscarProdutosPorCategoria(id, new ProdutoJsonPresenter());

        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(produtos));
        } else {
            return ResponseEntity.noContent().build();
        }

    }
}

