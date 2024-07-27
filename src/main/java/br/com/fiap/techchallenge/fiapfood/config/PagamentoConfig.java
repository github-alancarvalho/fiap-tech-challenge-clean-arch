package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.PagamentoController;
import br.com.fiap.techchallenge.fiapfood.external.PagamentoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PedidoUseCaseBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoConfig {

    @Bean
    PagamentoController getPagamentoController(PagamentoUseCaseBoundary pagamentoUseCaseBoundary, PedidoUseCaseBoundary pedidoUseCaseBoundary){
        return new PagamentoController(pagamentoUseCaseBoundary, pedidoUseCaseBoundary);
    }

    @Bean
    PagamentoUseCaseBoundary pagamentosUseCase(PagamentoGateway pagamentoGateway){
        return new PagamentoUseCase(pagamentoGateway);
    }

    @Bean
    PagamentoPresenter getPagamentoPresenter(){
        return new PagamentoJsonPresenter();

    }

    @Bean
    PagamentoGateway pagamentoRepositoryGateway() {
        return new PagamentoGatewayDataMapper();
    }

}
