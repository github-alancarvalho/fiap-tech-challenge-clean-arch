package br.com.fiap.techchallenge.fiapfood.adapters.presenters;


import br.com.fiap.techchallenge.fiapfood.dto.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.List;


public interface ClientePresenter {

    ClienteResponse prepararRespostaComSucesso(ClienteResponse clienteResponse);
    List<ClienteResponse> prepararRespostaListaComSucesso(List<ClienteResponse> listClienteResponse);
//    ClienteResponse prepararRespostaSemSucesso(FiapFoodException err);

    ClienteResponse prepararRespostaVazia();

    Boolean prepararRespostaComErroExcluir(FiapFoodException err);

    ClienteResponse prepararRespostaErro(FiapFoodException err);

    List<ClienteResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
