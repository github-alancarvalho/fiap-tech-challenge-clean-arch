package br.com.fiap.techchallenge.fiapfood.adapters.presenters;


import br.com.fiap.techchallenge.fiapfood.dto.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;

import java.util.List;


public interface PedidoPresenter {

    PedidoResponse prepararRespostaComSucesso(PedidoResponse pedidoResponse);
    List<PedidoResponse> prepararRespostaListaComSucesso(List<PedidoResponse> listPedidoResponse);

    PedidoResponse prepararRespostaVazia();

    Boolean prepararRespostaComErroExcluir(FiapFoodException err);

    PedidoResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<PedidoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
