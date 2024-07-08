package br.com.fiap.techchallenge.fiapfood.__presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;

import java.util.ArrayList;
import java.util.List;

public class ClienteJsonPresenter implements ClientePresenter{
    @Override
    public ClienteResponse prepararRespostaComSucesso(ClienteResponse categoriaResponse) {
        return categoriaResponse;
    }

    @Override
    public List<ClienteResponse> prepararRespostaListaComSucesso(List<ClienteResponse> listClienteResponse) {
        return listClienteResponse;
    }

    @Override
    public ClienteResponse prepararRespostaSemSucesso(FiapFoodException err) {
        return new ClienteResponse(null, null, null, null);
    }

    @Override
    public List<ClienteResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        return new ArrayList<ClienteResponse>();
    }
}
