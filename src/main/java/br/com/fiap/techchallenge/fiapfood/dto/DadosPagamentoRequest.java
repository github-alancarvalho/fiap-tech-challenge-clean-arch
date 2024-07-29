package br.com.fiap.techchallenge.fiapfood.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DadosPagamentoRequest {

    private String numeroCartao;
    private String anoVencimento;
    private String mesVencimento;
    private String cvv;
    private String nome;
    private String cpf;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getAnoVencimento() {
        return anoVencimento;
    }

    public void setAnoVencimento(String anoVencimento) {
        this.anoVencimento = anoVencimento;
    }

    public String getMesVencimento() {
        return mesVencimento;
    }

    public void setMesVencimento(String mesVencimento) {
        this.mesVencimento = mesVencimento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
