package br.com.fiap.techchallenge.fiapfood._config;

import br.com.fiap.techchallenge.fiapfood.__controller.ClienteController;
import br.com.fiap.techchallenge.fiapfood.__db.ClienteGatewayDataMapper;
import br.com.fiap.techchallenge.fiapfood.__gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClienteJsonPresenter;
import br.com.fiap.techchallenge.fiapfood.__presenters.ClientePresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.ClienteUseCase;
import br.com.fiap.techchallenge.fiapfood.__usecases.ClienteUseCaseBoundary;
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
        return new ClienteJsonPresenter();

    }

    @Bean
    ClienteGateway clienteRepositoryGateway() {
        return new ClienteGatewayDataMapper();
    }

}
