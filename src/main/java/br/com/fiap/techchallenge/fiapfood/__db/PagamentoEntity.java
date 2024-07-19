package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONE)
@Table(name = "pagamento")
@NamedQuery(name = "findAllPagamentos", query = "SELECT p FROM PagamentoEntity p")
public class PagamentoEntity implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@OneToOne
    @Column(name = "pedido_id", nullable = false)
    private Long idPedido;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @NotBlank
    @Column(name = "valor", nullable = false)
    private Double valor;


    public PagamentoEntity() {
    }

    public PagamentoEntity(Long id, Long idPedido, StatusPagamento status, Double valor) {
        this.id = id;
        this.idPedido = idPedido;
        this.status = status;
        this.valor = valor;
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

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
