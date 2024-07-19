package br.com.fiap.techchallenge.fiapfood._config;

import br.com.fiap.techchallenge.fiapfood.__controller.PedidoController;
import br.com.fiap.techchallenge.fiapfood.__db.ItemPedidoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.__db.PedidoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.ItemPedidoGateway;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.__presenters.PedidoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.PedidoPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.*;
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
