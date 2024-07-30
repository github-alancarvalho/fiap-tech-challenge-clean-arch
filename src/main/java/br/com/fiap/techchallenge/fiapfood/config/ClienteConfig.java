package br.com.fiap.techchallenge.fiapfood.config;

import br.com.fiap.techchallenge.fiapfood.adapters.controllers.ClienteController;
import br.com.fiap.techchallenge.fiapfood.external.ClienteGatewayImpl;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ClienteDefaultPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.ClientePresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ClienteUseCaseBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteConfig {

    @Bean
    ClienteController getClienteController(ClienteUseCaseBoundary clienteUseCaseBoundary, ClientePresenter clientePresenter){
        return new ClienteController(clienteUseCaseBoundary);
    }

    @Bean
    ClienteUseCaseBoundary clientesUseCase(ClienteGateway clienteGateway){
        return new ClienteUseCase(clienteGateway);
    }

    @Bean
    ClientePresenter getClientePresenter(){
        return new ClienteDefaultPresenter();

    }

    @Bean
    ClienteGateway clienteRepositoryGateway() {
        return new ClienteGatewayImpl();
    }

}
