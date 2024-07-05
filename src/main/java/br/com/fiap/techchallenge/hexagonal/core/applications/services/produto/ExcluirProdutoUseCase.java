package br.com.fiap.techchallenge.hexagonal.core.applications.services.produto;


import br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories.DaoFactory;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import br.com.fiap.techchallenge.hexagonal.core.domain.ports.output.ProdutoRepository;


public class ExcluirProdutoUseCase {

    private ProdutoRepository produtoRepository;


    public ExcluirProdutoUseCase() {
        this.produtoRepository = DaoFactory.getInstance().getProdutoRepositoryORM();
    }

    public Boolean excluir(Produto produto) {
        return this.produtoRepository.excluir(produto);
    }


}
