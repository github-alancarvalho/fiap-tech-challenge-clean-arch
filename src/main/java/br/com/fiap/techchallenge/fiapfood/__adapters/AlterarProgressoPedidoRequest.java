package br.com.fiap.techchallenge.fiapfood.__adapters;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlterarProgressoPedidoRequest {

    private Long id;
    private String novoStatus;

    public AlterarProgressoPedidoRequest() {
    }

    public AlterarProgressoPedidoRequest(Long id, String novoStatus) {
        this.id = id;
        this.novoStatus = novoStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(String novoStatus) {
        this.novoStatus = novoStatus;
    }
}
