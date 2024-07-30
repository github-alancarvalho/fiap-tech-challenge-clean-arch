package br.com.fiap.techchallenge.fiapfood.api;

import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoDefaultPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoDefaultPresenter;
import br.com.fiap.techchallenge.fiapfood.dto.AlterarProgressoPedidoRequest;
import br.com.fiap.techchallenge.fiapfood.dto.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.dto.PedidoRequest;
import br.com.fiap.techchallenge.fiapfood.dto.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.adapters.controllers.PedidoController;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
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
public class PedidoApiHandler {

    private final PedidoController pedidoController;

    public PedidoApiHandler(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }


    @Operation(summary = "Buscar PedidoEntity por Id", description = "Buscar PedidoEntity por Id")
    @GetMapping("/buscarPedidoPorId")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@RequestParam("id") Long id) {

        PedidoResponse pedido = this.pedidoController.buscarPedidoPorId(id, new PedidoDefaultPresenter());
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Alterar progresso do pedido", description = "Alterar progresso do pedido. ")
    @PutMapping("/{alterar}")
    public ResponseEntity<PedidoResponse> alterarProgressoPedido(@Valid @RequestBody AlterarProgressoPedidoRequest alterarProgressoPedidoRequest) {

        PedidoResponse pedido = this.pedidoController.alterarProgressoPedido(alterarProgressoPedidoRequest, new PedidoDefaultPresenter());

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @Operation(summary = "Excluir pedido por id", description = "Excluir pedido por id, sem pontuação")
//    @DeleteMapping("/{excluir}")
//    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
//        Pedido pedido = Pedido.builder()
//                .id(id).build();
//
//        Boolean isExcluded = pedidoController.excluir(id, new PedidoDefaultPresenter());
//        if (Boolean.TRUE.equals(isExcluded))
//            return ResponseEntity.noContent().build();
//        else
//            return ResponseEntity.badRequest().build();
//    }

    @Operation(summary = "Buscar todos os pedidos", description = "Buscar todos os pedidos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarTudo() {
        List<PedidoResponse> pedidos = pedidoController.buscarTodosPedidos(new PedidoDefaultPresenter());

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar pedidos por status", description = "Buscar pedidos por status. (RECEBIDO, EM_PREPARACAO, PRONTO, ENTRGUE)")
    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosPorStatus(@RequestParam("status") StatusPedido status) {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosPorStatus(status, new PedidoDefaultPresenter());

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @Operation(summary = "Buscar pedidos em aberto", description = "Buscar pedidos em aberto")
    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosEmAberto() {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosEmAberto(new PedidoDefaultPresenter());

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Buscar pedidos aguardando pagamento", description = "Buscar pedidos aguardando pagamento")
    @GetMapping("/buscarPedidosAguardandoPagamento")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosAguardandoPagamento() {

        List<PedidoResponse> pedidos = pedidoController.buscarPedidosAguardandoPagamento(new PedidoDefaultPresenter());

        if (!pedidos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pedidos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation(summary = "Checkout do PedidoEntity", description = "Checkout do PedidoEntity. PedidoEntity é inserido e aguardando pagamento")
    @PostMapping("/{checkout}")
    public ResponseEntity<PagamentoResponse> checkout(@Valid @RequestBody PedidoRequest pedidoRequest) {

        PagamentoResponse pagamentoResponse = pedidoController.checkout(pedidoRequest, new PagamentoDefaultPresenter());
        if (pagamentoResponse != null) {
            return ResponseEntity.ok(pagamentoResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}