package br.com.fiap.techchallenge.hexagonal.adapter.driver.controllers;

import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.PedidoRequest;
import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.PedidoResponse;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido.AtualizarPedidoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido.BuscarPedidoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido.CheckoutUseCase;
import br.com.fiap.techchallenge.hexagonal.core.domain.base.pedido.ExcluirPedidoUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name = "PedidoORM API")
@RestController
@RequestMapping("/api/v1/PedidosORM")
public class PedidoController {

    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;
    private final ExcluirPedidoUseCase excluirPedidoUseCase;
    private final CheckoutUseCase checkoutUseCase;

    public PedidoController() {

        this.buscarPedidoUseCase = new BuscarPedidoUseCase();
        this.atualizarPedidoUseCase = new AtualizarPedidoUseCase();
        this.excluirPedidoUseCase = new ExcluirPedidoUseCase();
        this.checkoutUseCase = new CheckoutUseCase();
    }

    @Operation(summary = "Checkout do PedidoORM", description = "Checkout do PedidoORM. PedidoORM é inserido e aguardando pagamento")
    @PostMapping("/{checkout}")
    public ResponseEntity<Optional<PedidoResponse>> checkout(@Valid @RequestBody PedidoRequest pedidoRequest) {

        Pedido pedido = Pedido.builder()
                .cliente(new Cliente(new Cpf(pedidoRequest.getCpfCliente()), null, null, null))
                .listItens(pedidoRequest.getListItens())
                .build();

        Optional<Pedido> savedPedido = checkoutUseCase.checkout(pedido);
        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar PedidoORM por Id", description = "Buscar PedidoORM por Id")
    @GetMapping("/buscarPedidoPorId")
    public ResponseEntity<Optional<PedidoResponse>> buscarPedidoPorId(@RequestParam("id") Long id) {

        Optional<Pedido> savedPedido = buscarPedidoUseCase.buscarPedidoPorId(id);
        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar pedido", description = "Alterar pedido. Id é mandatório")
    @PutMapping("/{alterarProgresso}")
    public ResponseEntity<Optional<PedidoResponse>> alterar(@Valid @RequestBody PedidoRequest pedidoRequest, @RequestParam("status") String status) {
        Pedido pedido = Pedido.builder()
                .id(pedidoRequest.getId())
                .status(StatusPedido.valueOf(status))
                .build();

        Optional<Pedido> savedPedido = atualizarPedidoUseCase.atualizarProgresso(pedido, StatusPedido.valueOf(status));

        if (!savedPedido.isEmpty()) {
            PedidoResponse response = PedidoResponse.builder()
                    .id(savedPedido.get().getId())
                    .cliente(savedPedido.get().getCliente())
                    .status(savedPedido.get().getStatus())
                    .listItens(savedPedido.get().getListItens())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir pedido por id", description = "Excluir pedido por id, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("id") Long id) {
        Pedido pedido = Pedido.builder()
                .id(id).build();

        Boolean isExcluded = excluirPedidoUseCase.excluir(pedido);
        if (Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os pedidos", description = "Buscar todos os pedidos")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarTudo() {
        Optional<List<Pedido>> pedidos = buscarPedidoUseCase.buscarTodosPedidos();
        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (Pedido pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar pedidos por status", description = "Buscar pedidos por status")
    @GetMapping("/buscarPedidosPorStatus")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosPorStatus(@RequestParam("status") String status) {
        Optional<List<Pedido>> pedidos = buscarPedidoUseCase.buscarPedidosPorStatus(
                StatusPedido.valueOf(status));

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (Pedido pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            if (!list.isEmpty())
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar pedidos em aberto", description = "Buscar pedidos em aberto")
    @GetMapping("/buscarPedidosEmAberto")
    public ResponseEntity<Optional<List<PedidoResponse>>> buscarPedidosEmAberto() {
        Optional<List<Pedido>> pedidos = buscarPedidoUseCase.buscarPedidosEmAberto();

        if (!pedidos.isEmpty()) {

            List<PedidoResponse> list = new ArrayList<>();
            for (Pedido pedido : pedidos.get()) {
                PedidoResponse response = PedidoResponse.builder()
                        .id(pedido.getId())
                        .cliente(pedido.getCliente())
                        .status(pedido.getStatus())
                        .listItens(pedido.getListItens())
                        .build();
                list.add(response);
            }
            if (!list.isEmpty())
                return ResponseEntity.ok(Optional.ofNullable(list));
            else
                return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

