package br.com.fiap.techchallenge.fiapfood.adapters.presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public PedidoResponse prepararRespostaVazia() {
        return null;
    }

    @Override
    public Boolean prepararRespostaComErroExcluir(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }
    @Override
    public PedidoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public List<PedidoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }
}
