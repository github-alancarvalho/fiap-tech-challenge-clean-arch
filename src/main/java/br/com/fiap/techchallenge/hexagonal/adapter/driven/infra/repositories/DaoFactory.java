package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories;

import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.*;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ClienteRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PagamentoRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.PedidoRepository;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ProdutoRepository;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.ClienteDao;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.PagamentoDao;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.PedidoDao;
import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.mariadb.ProdutoDao;

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


    public ClienteRepository getClienteRepositoryORM() {
        return new ClienteDao();
    }

    public ProdutoRepository getProdutoRepositoryORM() {
        return new ProdutoDao();
    }

    public PedidoRepository getPedidoRepositoryORM() {
        return new PedidoDao();
    }

    public PagamentoRepository getPagamentoRepositoryORM() {
        return new PagamentoDao();
    }

}
