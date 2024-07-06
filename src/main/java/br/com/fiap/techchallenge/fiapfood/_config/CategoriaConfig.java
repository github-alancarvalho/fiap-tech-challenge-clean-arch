package br.com.fiap.techchallenge.fiapfood._config;

import br.com.fiap.techchallenge.fiapfood.__controller.CategoriaController;
import br.com.fiap.techchallenge.fiapfood.__db.CategoriaGatewayJPAClean;
import br.com.fiap.techchallenge.fiapfood.__gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood.__presenters.CategoriaJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.CategoriaPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.CategoriaUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.CategoriaUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood._infra.gateway_apagar.CategoriaRepositoryApagarImpl;
import br.com.fiap.techchallenge.fiapfood._infra.persistence.CategoriaRepository_apagar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaConfig {

    @Bean
    CategoriaController getCategoriaController(CategoriaUseCaseBoundary categoriaUseCaseBoundary, CategoriaPresenter categoriaPresenter){
        return new CategoriaController(categoriaUseCaseBoundary);
    }

    @Bean
    CategoriaUseCaseBoundary buscarCategoriasUseCase(CategoriaGateway categoriaGateway){
        return new CategoriaUseCase(categoriaGateway);
    }

    @Bean
    CategoriaPresenter getCategoriaPresenter(){
        return new CategoriaJsonPresenter();

    }

    @Bean
    CategoriaGateway categoriaRepositoryGateway() {
        return new CategoriaGatewayJPAClean();
    }

    @Bean
    CategoriaRepository_apagar categoriaRepository(){
        return new CategoriaRepositoryApagarImpl();
    }
}
