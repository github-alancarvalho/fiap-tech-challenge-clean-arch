package br.com.fiap.techchallenge.fiapfood.__api;

import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__controller.ClienteController;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClienteJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClientePresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "ClienteEntity API")
@RestController
@RequestMapping("/api/v1/Clientes")
public class ClienteApi {

    private final ClienteController clienteController;

    public ClienteApi(ClienteController clienteController) {
        this.clienteController = clienteController;

    }

    @Operation(summary = "Inserir ClienteEntity", description = "Inserir novo ClienteEntity")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ClienteResponse>> inserir(@Valid @RequestBody ClienteRequest clienteRequest) {

        ClienteResponse savedCliente = this.clienteController.inserir(clienteRequest, new ClienteJsonPresenter());

        if (savedCliente != null)
            return ResponseEntity.ok(Optional.ofNullable(savedCliente));
        else
            return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Buscar ClienteEntity por Cpf", description = "Buscar ClienteEntity por Cpf")
    @GetMapping("/buscarClientePorCpf")
    public ResponseEntity<ClienteResponse> buscarClientePorCpf(@RequestParam("cpf") String cpf) {

        ClienteResponse cliente = this.clienteController.buscarClientePorCpf(cpf, new ClienteJsonPresenter());
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @Operation(summary = "Alterar cliente", description = "Alterar ClienteEntity. Cpf é mandatório")
    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ClienteResponse>> alterar(@Valid @RequestBody ClienteRequest clienteRequest) {

        ClienteResponse savedCliente = this.clienteController.alterar(clienteRequest, new ClienteJsonPresenter());

        if (savedCliente != null) {
            return ResponseEntity.ok(Optional.ofNullable(savedCliente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir ClienteEntity por Cpf", description = "Excluir ClienteEntity por Cpf, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("cpf") String cpf) {

        Boolean isExcluded = this.clienteController.excluir(cpf);
        if (Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();

    }

    @Operation(summary = "Buscar todos os clientes", description = "Buscar todos os clientes")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ClienteResponse>>> buscarTudo() {

        List<ClienteResponse> clientes = this.clienteController.buscarTodosClientes(new ClienteJsonPresenter());

        if (!clientes.isEmpty()) {
            return ResponseEntity.ok(Optional.ofNullable(clientes));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}

