package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.CategoriaController;
import br.com.fiap.techchallenge.fiapfood.external.CategoriaGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.CategoriaGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.CategoriaJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.CategoriaPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.CategoriaUseCase;
import br.com.fiap.techchallenge.fiapfood.core.usecases.CategoriaUseCaseBoundary;
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
        return new CategoriaGatewayDataMapper();
    }


}
