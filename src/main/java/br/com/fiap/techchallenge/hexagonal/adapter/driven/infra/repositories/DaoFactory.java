package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories;

import br.com.fiap.techchallenge.fiapfood.__gateways.ClienteGateway;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood.__gateways.PedidoGateway;
import br.com.fiap.techchallenge.fiapfood.__gateways.ProdutoGateway;

public class DaoFactory {

    private static DaoFactory daoFactory;
    //public static final DaoFactory INSTANCE = new DaoFactory();
    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
            return daoFactory;
        } else
            return daoFactory;
    }


    public ClienteGateway getClienteRepositoryORM() {
        return null;
    }

    public ProdutoGateway getProdutoRepositoryORM() {
        return null;
    }

    public PedidoGateway getPedidoRepositoryORM() {
        return null;
    }

    public PagamentoGateway getPagamentoRepositoryORM() {
        return null;
    }

}
