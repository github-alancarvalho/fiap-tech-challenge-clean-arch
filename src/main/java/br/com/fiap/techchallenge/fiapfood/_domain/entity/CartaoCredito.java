package br.com.fiap.techchallenge.fiapfood._domain.entity;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartaoCredito {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String numero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer anoVencimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer mesVencimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cvv;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Cpf cpf;


    public CartaoCredito(String numero, Integer anoVencimento, Integer mesVencimento, Integer cvv, Cpf cpf) {
        this.numero = numero;
        this.anoVencimento = anoVencimento;
        this.mesVencimento = mesVencimento;
        this.cvv = cvv;
        this.cpf = cpf;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getAnoVencimento() {
        return anoVencimento;
    }

    public void setAnoVencimento(Integer anoVencimento) {
        this.anoVencimento = anoVencimento;
    }

    public Integer getMesVencimento() {
        return mesVencimento;
    }

    public void setMesVencimento(Integer mesVencimento) {
        this.mesVencimento = mesVencimento;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }
}
