package br.com.fiap.techchallenge.hexagonal.adapter.driver.controllers;

import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.ClienteRequest;
import br.com.fiap.techchallenge.hexagonal.adapter.driver.web.ClienteResponse;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente.AtualizarClienteUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente.BuscarClienteUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente.ExcluirClienteUseCase;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.cliente.InserirClienteUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Cliente;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Cpf;
import br.com.fiap.techchallenge.hexagonal.core.domain.valueobject.Telefone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "ClienteORM API")
@RestController
@RequestMapping("/api/v1/ClientesORM")
public class ClienteController {

    private final InserirClienteUseCase inserirClienteUseCase;
    private final BuscarClienteUseCase buscarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;

    public ClienteController() {

        this.inserirClienteUseCase = new InserirClienteUseCase();
        this.buscarClienteUseCase = new BuscarClienteUseCase();
        this.atualizarClienteUseCase = new AtualizarClienteUseCase();
        this.excluirClienteUseCase = new ExcluirClienteUseCase();
    }

    @Operation(summary = "Inserir ClienteORM", description = "Inserir novo ClienteORM")
    @PostMapping("/{inserir}")
    public ResponseEntity<Optional<ClienteResponse>> inserir(@Valid @RequestBody ClienteRequest clienteRequest) {

        Cliente cliente = Cliente.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<Cliente> savedCliente = inserirClienteUseCase.inserirClienteORM(cliente);

        if (!savedCliente.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(savedCliente.get().getCpf().getCpfSomenteNumero())
                    .nome(savedCliente.get().getNome())
                    .email(savedCliente.get().getEmail())
                    .telefone(savedCliente.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Buscar ClienteORM por Cpf", description = "Buscar ClienteORM por Cpf")
    @GetMapping("/buscarClientePorCpf")
    public ResponseEntity<Optional<ClienteResponse>> buscarClientePorCpf(@RequestParam("cpf") String cpf) {

        Optional<Cliente> cliente = buscarClienteUseCase.buscarClientePorCpfORM(new Cpf(cpf));
        if (!cliente.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(cliente.get().getCpf().getCpfSomenteNumero())
                    .nome(cliente.get().getNome())
                    .email(cliente.get().getEmail())
                    .telefone(cliente.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Alterar cliente", description = "Alterar ClienteORM. Cpf é mandatório")
    @PutMapping("/{alterar}")
    public ResponseEntity<Optional<ClienteResponse>> alterar(@Valid @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .cpf(new Cpf(clienteRequest.getCpf()))
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .telefone(new Telefone(clienteRequest.getTelefone())).build();

        Optional<Cliente> savedCliente = atualizarClienteUseCase.atualizar(cliente);

        if (!savedCliente.isEmpty()) {
            ClienteResponse response = ClienteResponse.builder()
                    .cpf(savedCliente.get().getCpf().getCpfSomenteNumero())
                    .nome(savedCliente.get().getNome())
                    .email(savedCliente.get().getEmail())
                    .telefone(savedCliente.get().getTelefone().getTelefone()).build();

            return ResponseEntity.ok(Optional.ofNullable(response));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir ClienteORM por Cpf", description = "Excluir ClienteORM por Cpf, sem pontuação")
    @DeleteMapping("/{excluir}")
    public ResponseEntity<Optional<Boolean>> excluir(@RequestParam("cpf") String cpf) {

        Boolean isExcluded = excluirClienteUseCase.excluir(new Cpf(cpf));
        if(Boolean.TRUE.equals(isExcluded))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Buscar todos os clientes", description = "Buscar todos os clientes")
    @GetMapping("/buscarTudo")
    public ResponseEntity<Optional<List<ClienteResponse>>> buscarTudo() {
        Optional<List<Cliente>> clientes = buscarClienteUseCase.buscarTodosClientes();
        if (!clientes.isEmpty()) {

            List<ClienteResponse> list = new ArrayList<>();
            for (Cliente cliente : clientes.get()) {
                ClienteResponse response = ClienteResponse.builder()
                        .cpf(cliente.getCpf().getCpfSomenteNumero())
                        .nome(cliente.getNome())
                        .email(cliente.getEmail())
                        .telefone(cliente.getTelefone().getTelefone()).build();
                list.add(response);
            }
            return ResponseEntity.ok(Optional.ofNullable(list));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

