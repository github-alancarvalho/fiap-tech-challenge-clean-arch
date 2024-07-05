package br.com.fiap.techchallenge.hexagonal.adapter.driver.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequest {

    @NotEmpty(message = "Cpf não pode ser vazio")
    @NotNull(message = "Cpf não pode ser nulo")
    @Schema(description = "Cpf do cliente,", example = "77248715077")
    private String cpf;

    @NotEmpty(message = "Nome não pode ser vazio")
    @NotNull(message = "Nome não pode ser nulo")
    @Schema(description = "Nome do cliente,", example = "Jõao da Silva")
    private String nome;

    @NotEmpty(message = "E-mail não pode ser vazio")
    @NotNull(message = "E-mail não pode ser nulo")
    @Schema(description = "E-mail do cliente,", example = "joao@teste.com.br")
    @Email
    private String email;

    @NotEmpty(message = "Telefone não pode ser vazio")
    @NotNull(message = "Telefone não pode ser nulo")
    @Schema(description = "Telefone do cliente,", example = "11123456789")
    private String telefone;

    public ClienteRequest() {
    }

    public ClienteRequest(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
