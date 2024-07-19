package br.com.fiap.techchallenge.fiapfood.__adapters;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PagamentoMercadoPagoDto {

    @NotNull
    private BigDecimal transactionAmount;

    @NotNull
    @JsonProperty("description")
    private String productDescription;

    @NotNull
    private PayerDto payer;

    public PagamentoMercadoPagoDto() {
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public PayerDto getPayer() {
        return payer;
    }

    public void setPayer(PayerDto payer) {
        this.payer = payer;
    }
}
