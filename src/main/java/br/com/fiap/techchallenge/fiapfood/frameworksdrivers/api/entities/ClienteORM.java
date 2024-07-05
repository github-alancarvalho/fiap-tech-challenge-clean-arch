package br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
@NamedQuery(name = "findAllClientes", query = "SELECT c FROM ClienteORM c")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteORM {

    public ClienteORM() {
    }

    public ClienteORM(String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    @NotNull
    @Id
    @Column(name = "cpf", nullable = false)
    private String cpf;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
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

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}