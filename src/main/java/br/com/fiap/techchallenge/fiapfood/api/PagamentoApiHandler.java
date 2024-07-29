package br.com.fiap.techchallenge.fiapfood.api;

import br.com.fiap.techchallenge.fiapfood.dto.AtualizarPagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.dto.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.adapters.controllers.PagamentoController;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Tag(name = "PagamentoEntity API")
@RestController
@RequestMapping("/api/v1/Pagamentos")
public class PagamentoApiHandler {

    private final PagamentoController pagamentoController;

    public PagamentoApiHandler(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Listener para receber a confirmacao de pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<String> receberConfirmacaoDePagamento(@RequestParam Map<String, String> params, @RequestBody String body) {
        System.out.println(body);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(body, Map.class);

            Object idValue = data.get("data");
            if (idValue instanceof Map) {
                Map<String, Object> dataMap = (Map<String, Object>) idValue;
                String idPagamento = (String) dataMap.get("id");
                System.out.println("Extracted ID: " + idPagamento);
                return ResponseEntity.ok(this.pagamentoController.receberConfirmacaoDePagamento(Long.valueOf(idPagamento), new PagamentoJsonPresenter()));
            } else {
                System.out.println("Unexpected data format for 'data'");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    @Operation(summary = "Buscar PagamentoEntity por Id", description = "Buscar PagamentoEntity por Id")
    @GetMapping("/buscarPagamentoPorId")
    public ResponseEntity<PagamentoResponse> buscarPagamentoPorId(@RequestParam("id") Long id) {

        PagamentoResponse pagamento =  this.pagamentoController.buscarPagamentoPorId(id, new PagamentoJsonPresenter());
        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar Pagamento por Id do Pedido", description = "Buscar Pagamento por Id do Pedido")
    @GetMapping("/buscarPagamentoPorIdPedido")
    public ResponseEntity<PagamentoResponse> buscarPagamentoPorIdPedido(@RequestParam("id") Long id) {

        PagamentoResponse pagamento =  this.pagamentoController.buscarPagamentoPorIdPedido(id, new PagamentoJsonPresenter());
        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Atualizar pagamento", description = "Alterar pagamento. Id é mandatório")
    @PutMapping("/{atualizarPagamento}")
    public ResponseEntity<PagamentoResponse> atualizarPagamento(@Valid @RequestBody AtualizarPagamentoRequest atualizarPagamentoRequest, @RequestParam("status")StatusPagamento status) {

        PagamentoResponse pagamento = this.pagamentoController.atualizarPagamento(atualizarPagamentoRequest, status, new PagamentoJsonPresenter());

        if (pagamento != null) {
            return ResponseEntity.ok(pagamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar todos os pagamentos", description = "Buscar todos os pagamentos")
    @GetMapping("/buscarTodosPagamentos")
    public ResponseEntity<Optional<List<PagamentoResponse>>> buscarTodosPagamentos() {
        List<PagamentoResponse> pagamentos = pagamentoController.buscarTodosPagamentos(new PagamentoJsonPresenter());

        if (!pagamentos.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(pagamentos));
        } else {
            return ResponseEntity.noContent().build();
        }
    }


}

