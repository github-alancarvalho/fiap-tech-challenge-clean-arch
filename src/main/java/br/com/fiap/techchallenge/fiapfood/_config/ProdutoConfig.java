package br.com.fiap.techchallenge.fiapfood._config;

import br.com.fiap.techchallenge.fiapfood.__controller.ProdutoController;
import br.com.fiap.techchallenge.fiapfood.__db.ProdutoGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.ProdutoGateway;
import br.com.fiap.techchallenge.fiapfood.__presenters.ProdutoJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.ProdutoPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCaseBoundary;
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
        return new ProdutoJsonPresenter();

    }

    @Bean
    ProdutoGateway produtoRepositoryGateway() {
        return new ProdutoGatewayDataMapper();
    }

}
