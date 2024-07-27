package br.com.fiap.techchallenge.fiapfood.adapters.presenters;

import br.com.fiap.techchallenge.fiapfood.__adapters.ClienteResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ClienteDefaultPresenter implements ClientePresenter{
    @Override
    public ClienteResponse prepararRespostaComSucesso(ClienteResponse categoriaResponse) {
        return categoriaResponse;
    }

    @Override
    public List<ClienteResponse> prepararRespostaListaComSucesso(List<ClienteResponse> listClienteResponse) {
        return listClienteResponse;
    }

    @Override
    public ClienteResponse prepararRespostaVazia() {
        return null;
    }

    @Override
    public Boolean prepararRespostaComErroExcluir(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public ClienteResponse prepararRespostaErro(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
    }

    @Override
    public List<ClienteResponse> prepararRespostaListaSemSucesso(FiapFoodException err) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
//        return new ArrayList<ClienteResponse>();
    }
}
