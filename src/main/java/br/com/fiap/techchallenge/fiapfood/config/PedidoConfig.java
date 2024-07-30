package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.PedidoController;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoDefaultPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.*;
import br.com.fiap.techchallenge.fiapfood.external.ItemPedidoGatewayImpl;
import br.com.fiap.techchallenge.fiapfood.external.PedidoGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    PedidoController getPedidoController(PedidoUseCaseBoundary pedidoUseCaseBoundary, ProdutoUseCaseBoundary produtoUseCaseBoundary, PagamentoUseCaseBoundary pagamentoUseCaseBoundary) {
        return new PedidoController(pedidoUseCaseBoundary, produtoUseCaseBoundary, pagamentoUseCaseBoundary);
    }

    @Bean
    PedidoUseCaseBoundary pedidosUseCase(PedidoGateway pedidoGateway,
                                         ItemPedidoGateway itemPedidoGateway,
                                         ClienteUseCaseBoundary clienteUseCaseBoundary,
                                         ProdutoUseCaseBoundary produtoUseCaseBoundary,
                                         PagamentoUseCaseBoundary pagamentoUseCaseBoundary) {
        return new PedidoUseCase(pedidoGateway, itemPedidoGateway, clienteUseCaseBoundary, produtoUseCaseBoundary, pagamentoUseCaseBoundary);
    }

    @Bean
    PedidoPresenter getPedidoPresenter() {
        return new PedidoDefaultPresenter();

    }

    @Bean
    PedidoGateway pedidoRepositoryGateway() {
        return new PedidoGatewayImpl();
    }

    @Bean
    ItemPedidoGateway itemPedidoRepositoryGateway() {
        return new ItemPedidoGatewayImpl();
    }
}
