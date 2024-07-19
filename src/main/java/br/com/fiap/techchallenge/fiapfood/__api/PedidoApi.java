package br.com.fiap.techchallenge.fiapfood.__api;

import br.com.fiap.techchallenge.fiapfood.__adapters.AlterarProgressoPedidoRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__controller.PedidoController;
import br.com.fiap.techchallenge.fiapfood.__presenters.PedidoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "PedidoEntity API")
@RestController
@RequestMapping("/api/v1/Pedidos")
public class PedidoApi {

    private final PedidoController pedidoController;

    public PedidoApi(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

//    @Operation(summary = "Inserir PedidoEntity", description = "Inserir novo PedidoEntity")
//    @PostMapping("/{inserir}")
//    public ResponseEntity<Optional<PedidoResponse>> inserir(@Valid @RequestBody PedidoRequest pedidoRequest) {
//
//        PedidoResponse savedPedido = pedidoController.inserir(pedidoRequest, new PedidoJsonPresenter());
//
//        if (savedPedido != null)
//            return ResponseEntity.ok(Optional.ofNullable(savedPedido));
//        else
//            return ResponseEntity.notFound().build();
//
//    }

    @Operation(summary = "Buscar PedidoEntity por Id", description = "Buscar PedidoEntity por Id")
    @GetMapping("/buscarPedidoPorId")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@RequestParam("id") Long id) {

        PedidoResponse pedido = this.pedidoController.buscarPedidoPorId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar progresso do pedido", description = "Alterar progresso do pedido. ")
    @PutMapping("/{alterar}")
    public ResponseEntity<PedidoResponse> alterarProgressoPedido(@Valid @RequestBody AlterarProgressoPedidoRequest alterarProgressoPedidoRequest) {

        PedidoResponse pedido = this.pedidoController.alterarProgressoPedido(alterarProgressoPedidoRequest);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir pedido por id", description = "Excluir pedido por id, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        Pedido pedido = Pedido.builder()
                .id(id).build();

        Boolean isExcluded = pedidoController.excluir(id);
        if (Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os pedidos", description = "Buscar todos os pedidos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarTudo() {
        List<PedidoResponse> pedidos = pedidoController.buscarTodosPedidos();

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar pedidos por status", description = "Buscar pedidos por status. (RECEBIDO, EM_PREPARACAO, PRONTO, ENTRGUE)")
    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosPorStatus(@RequestParam("status") StatusPedido status) {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosPorStatus(status);

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @Operation(summary = "Buscar pedidos em aberto", description = "Buscar pedidos em aberto")
    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosEmAberto() {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosEmAberto();

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar pedidos aguardando pagamento", description = "Buscar pedidos aguardando pagamento")
    @GetMapping("/buscarPedidosAguardandoPagamento")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosAguardandoPagamento() {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosAguardandoPagamento();

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Checkout do PedidoEntity", description = "Checkout do PedidoEntity. PedidoEntity é inserido e aguardando pagamento")
    @PostMapping("/{checkout}")
    public ResponseEntity<PagamentoResponse> checkout(@Valid @RequestBody PedidoRequest pedidoRequest) {

        PagamentoResponse pagamentoResponse = pedidoController.checkout(pedidoRequest);
        if (pagamentoResponse != null) {
            return ResponseEntity.ok(pagamentoResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}