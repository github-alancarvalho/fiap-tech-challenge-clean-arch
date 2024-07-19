package br.com.fiap.techchallenge.fiapfood.__presenters;


import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.List;


public interface PedidoPresenter {

    PedidoResponse prepararRespostaComSucesso(PedidoResponse pedidoResponse);
    List<PedidoResponse> prepararRespostaListaComSucesso(List<PedidoResponse> listPedidoResponse);
    PedidoResponse prepararRespostaSemSucesso(FiapFoodException err);
    List<PedidoResponse> prepararRespostaListaSemSucesso(FiapFoodException err);
}
