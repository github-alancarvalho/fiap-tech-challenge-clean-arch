package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.ProdutoController;
import br.com.fiap.techchallenge.fiapfood.external.ProdutoGatewayImpl;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ProdutoDefaultPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ProdutoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ProdutoUseCaseBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoConfig {

    @Bean
    ProdutoController getProdutoController(ProdutoUseCaseBoundary produtoUseCaseBoundary, ProdutoPresenter produtoPresenter){
        return new ProdutoController(produtoUseCaseBoundary);
    }

    @Bean
    ProdutoUseCaseBoundary produtosUseCase(ProdutoGateway produtoGateway){
        return new ProdutoUseCase(produtoGateway);
    }

    @Bean
    ProdutoPresenter getProdutoPresenter(){
        return new ProdutoDefaultPresenter();

    }

    @Bean
    ProdutoGateway produtoRepositoryGateway() {
        return new ProdutoGatewayImpl();
    }

}
