package br.com.fiap.techchallenge.hexagonal.core.applications.services.produto;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ProdutoRepository;

import java.util.Optional;


public class InserirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public InserirProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Optional<Produto> inserir(Produto produto) {
        return this.produtoRepository.inserir(produto);
    }
}
