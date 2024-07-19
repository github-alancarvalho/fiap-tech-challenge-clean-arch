package br.com.fiap.techchallenge.fiapfood.__adapters;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtualizarPagamentoRequest {

    @Schema(description = "Id do Pagamento,", example = "123")
    private Long id;

    @Schema(description = "Código do Pedido,", example = "788")
    private Long idPedido;

    @NotEmpty(message = "Status do Pagamento não pode ser vazio")
    @NotNull(message = "Status do Pagamento não pode ser nulo")
    @Schema(description = "Status do Pagamento do pedido,", example = "CONFIRMADO")
    @Email
    private String status;


    public AtualizarPagamentoRequest() {
    }

    public AtualizarPagamentoRequest(Long id, Long idPedido, String status) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
