package br.com.fiap.techchallenge.fiapfood.__presenters;


import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.List;


public interface ClientePresenter {

    ClienteResponse prepararRespostaComSucesso(ClienteResponse clienteResponse);
    List<ClienteResponse> prepararRespostaListaComSucesso(List<ClienteResponse> listClienteResponse);
    ClienteResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<ClienteResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
