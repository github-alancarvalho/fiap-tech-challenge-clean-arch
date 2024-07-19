package br.com.fiap.techchallenge.fiapfood._config;

import br.com.fiap.techchallenge.fiapfood.__controller.PagamentoController;
import br.com.fiap.techchallenge.fiapfood.__db.PagamentoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood.__presenters.PagamentoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.PagamentoPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.PagamentoUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.__usecases.PedidoUseCaseBoundary;
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
