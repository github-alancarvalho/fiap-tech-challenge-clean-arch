package br.com.fiap.techchallenge.hexagonal.adapter.driver.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteResponse {

    @Schema(description = "Cpf do cliente,", example = "12345678900")
    private String cpf;

    @Schema(description = "Nome do cliente,", example = "Jõao da Silva")
    private String nome;

    @Schema(description = "E-mail do cliente,", example = "joao@teste.com.br")
    private String email;

    @Schema(description = "Telefone do cliente,", example = "joao@teste.com.br")
    private String telefone;

    public ClienteResponse() {
    }

    public ClienteResponse(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

}
