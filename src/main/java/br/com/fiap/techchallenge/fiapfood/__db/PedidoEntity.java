package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Table(name = "pedido")
@NamedQuery(name = "findAllPedidos", query = "SELECT p FROM PedidoEntity p")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_cpf")
    private ClienteEntity clienteEntity;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private LocalDateTime data;

    @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedidoEntity> listItens = new ArrayList<>();

    public PedidoEntity() {
    }

    public PedidoEntity(Long id, ClienteEntity clienteEntity, StatusPedido status, LocalDateTime data, List<ItemPedidoEntity> listItens) {
        this.id = id;
        this.clienteEntity = clienteEntity;
        this.status = status;
        this.data = data;
        this.listItens = listItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteEntity getCliente() {
        return clienteEntity;
    }

    public void setCliente(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemPedidoEntity> getListItens() {
        return listItens;
    }

    public void setListItens(List<ItemPedidoEntity> listItens) {
        this.listItens = listItens;
    }
}
