package br.com.fiap.techchallenge.fiapfood.frameworksdrivers.api.entities;

import br.com.fiap.techchallenge.hexagonal.core.domain.base.StatusPedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Table(name = "pedido")
@NamedQuery(name = "findAllPedidos", query = "SELECT p FROM PedidoORM p")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_cpf")
    private ClienteORM clienteORM;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedidoORM", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedidoORM> listItens = new ArrayList<>();

    public PedidoORM() {
    }

    public PedidoORM(Long id, ClienteORM clienteORM, StatusPedido status, List<ItemPedidoORM> listItens) {
        this.id = id;
        this.clienteORM = clienteORM;
        this.status = status;
        this.listItens = listItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteORM getCliente() {
        return clienteORM;
    }

    public void setCliente(ClienteORM clienteORM) {
        this.clienteORM = clienteORM;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedidoORM> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoORM> listItens) {
        this.listItens = listItens;
    }
}
