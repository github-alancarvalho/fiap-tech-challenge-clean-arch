package br.com.fiap.techchallenge.fiapfood.adapters.presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.PedidoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class PagamentoJsonPresenter implements PagamentoPresenter{
    @Override
    public PagamentoResponse prepararRespostaComSucesso(PagamentoResponse pagamentoResponse) {
        return pagamentoResponse;
    }

    @Override
    public List<PagamentoResponse> prepararRespostaListaComSucesso(List<PagamentoResponse> listPagamentoResponse) {
        return listPagamentoResponse;
    }

    @Override
    public PagamentoResponse prepararRespostaVazia() {
        return null;
    }

    @Override
    public String prepararResposta(PedidoResponse pedidoResponse) {
        if (pedidoResponse != null)
            return "Pedido recebido com sucesso";
        else
            return "Problema para receber o pedido";
    }


    @Override
    public Boolean prepararRespostaComErroExcluir(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }


    @Override
    public PagamentoResponse prepararRespostaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public String prepararRespostaSemSucesso(JsonProcessingException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public List<PagamentoResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }
}
