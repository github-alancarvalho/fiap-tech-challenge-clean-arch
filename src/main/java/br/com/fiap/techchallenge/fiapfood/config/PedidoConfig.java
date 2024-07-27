package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.PedidoController;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.*;
import br.com.fiap.techchallenge.fiapfood.external.ItemPedidoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.external.PedidoGatewayDataMapper;
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
        return new PedidoJsonPresenter();

    }

    @Bean
    PedidoGateway pedidoRepositoryGateway() {
        return new PedidoGatewayDataMapper();
    }

    @Bean
    ItemPedidoGateway itemPedidoRepositoryGateway() {
        return new ItemPedidoGatewayDataMapper();
    }
}
