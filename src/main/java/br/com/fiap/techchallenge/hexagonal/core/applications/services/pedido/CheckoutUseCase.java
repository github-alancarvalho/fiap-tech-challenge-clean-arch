package br.com.fiap.techchallenge.hexagonal.core.applications.services.pedido;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.hexagonal.core.applications.services.pagamento.ProcessarPagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pedido;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;

import java.util.Optional;


public class CheckoutUseCase {

    private PedidoGateway pedidoGateway;
    private ProcessarPagamentoUseCase processarPagamentoUseCase;


    public CheckoutUseCase() {
        this.pedidoGateway = DaoFactory.getInstance().getPedidoRepositoryORM();
        this.processarPagamentoUseCase = new ProcessarPagamentoUseCase();
    }

    public Optional<Pedido> checkout(Pedido pedidoDto) {
        Optional<Pedido> pedido = this.pedidoGateway.inserir(pedidoDto);

        Pagamento pagamento = new Pagamento();
        pagamento.setIdPedido(pedido.isPresent() ? pedido.get().getId() : null);
        pagamento.setStatus(StatusPagamento.EM_PROCESSAMENTO);
        pagamento.setValor(calcularValorTotalDoPedido(pedido.isPresent() ? pedido.get() : null));
        processarPagamentoUseCase.processarPagamento(pagamento);

        return pedido;
    }

    public Double calcularValorTotalDoPedido(Pedido pedido) {

        Double valorTotalPedido = 0D;
        Double valorProduto;

        for(ItemPedido ip : pedido.getListItens()){
            valorProduto = ip.getProduto().getPreco();
            valorTotalPedido+= valorProduto * ip.getQuantidade();
        }
        return valorTotalPedido;
    }
}
