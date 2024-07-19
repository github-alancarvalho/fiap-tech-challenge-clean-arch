package br.com.fiap.techchallenge.fiapfood.__presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.ArrayList;
import java.util.List;

public class PedidoJsonPresenter implements PedidoPresenter{
    @Override
    public PedidoResponse prepararRespostaComSucesso(PedidoResponse pedidoResponse) {
        return pedidoResponse;
    }

    @Override
    public List<PedidoResponse> prepararRespostaListaComSucesso(List<PedidoResponse> listPedidoResponse) {
        return listPedidoResponse;
    }

    @Override
    public PedidoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        return new PedidoResponse(null, null, null, null, null);
    }

    @Override
    public List<PedidoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        return new ArrayList<PedidoResponse>();
    }
}
