package br.com.fiap.techchallenge.hexagonal.adapter.driver.controllers;

import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento.AtualizarPagamentoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento.BuscarPagamentoUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento.ProcessarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "PagamentoEntity API")
@RestController
@RequestMapping("/api/v1/PagamentosORM")
public class PagamentoController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;
    private final BuscarPagamentoUseCase buscarPagamentoUseCase;
    private final AtualizarPagamentoUseCase atualizarPagamentoUseCase;

    public PagamentoController() {
        this.buscarPagamentoUseCase = new BuscarPagamentoUseCase();
        this.atualizarPagamentoUseCase = new AtualizarPagamentoUseCase();
        this.processarPagamentoUseCase = new ProcessarPagamentoUseCase();
    }

    @Operation(summary = "Processar pagamento", description = "Processar pagamento")
    @PostMapping("/{processarPagamento}")
    public ResponseEntity<Optional<PagamentoResponse>> processarPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = Pagamento.builder()
                .idPedido(pagamentoRequest.getIdPedido())
                .valor(pagamentoRequest.getValor())
                .build();

        Optional<Pagamento> savedPagamento = processarPagamentoUseCase.processarPagamento(pagamento);
        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar pagamento por Id", description = "Buscar pagamento por Id")
    @GetMapping("/buscarPagamentoPorId")
    public ResponseEntity<Optional<PagamentoResponse>> buscarPagamentoPorId(@RequestParam("id") Long id) {

        Optional<Pagamento> savedPagamento = buscarPagamentoUseCase.buscarPagamentoPorId(id);
        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar o progresso do pagamento", description = "Atualizar o progresso do pagamento (EM_PROCESSAMENTO, CONFIRMADO)")
    @PutMapping("/{atualizarProgressoPagamento}")
    public ResponseEntity<Optional<PagamentoResponse>> atualizarProgressoPagamento(@Valid @RequestBody PagamentoRequest pagamentoRequest, @RequestParam("status") String status) {
        Pagamento pagamento = Pagamento.builder()
                .id(pagamentoRequest.getId())
                .status(StatusPagamento.valueOf(status))
                .build();

        Optional<Pagamento> savedPagamento = atualizarPagamentoUseCase.atualizarProgressoPagamento(pagamento, StatusPagamento.valueOf(status));

        if (!savedPagamento.isEmpty()) {
            PagamentoResponse response = PagamentoResponse.builder()
                    .id(savedPagamento.get().getId())
                    .idPedido(savedPagamento.get().getIdPedido())
                    .valor(savedPagamento.get().getValor())
                    .status(savedPagamento.get().getStatus().toString())
                    .build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar todos os pagamentos", description = "Buscar todos os pagamento")
    @GetMapping("/buscarTodosPagamentos")
    public ResponseEntity<Optional<List<PagamentoResponse>>> buscarTodosPagamentos() {
        Optional<List<Pagamento>> pagamentos = buscarPagamentoUseCase.buscarTodosPagamentos();
        if (!pagamentos.isEmpty()) {

            List<PagamentoResponse> list = new ArrayList<>();
            for (Pagamento pagamento : pagamentos.get()) {
                PagamentoResponse response = PagamentoResponse.builder()
                        .id(pagamento.getId())
                        .idPedido(pagamento.getIdPedido())
                        .valor(pagamento.getValor())
                        .status(pagamento.getStatus().toString())
                        .build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

