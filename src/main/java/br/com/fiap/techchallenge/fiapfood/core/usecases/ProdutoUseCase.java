package br.com.fiap.techchallenge.fiapfood.core.usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood.core.entity.Produto;

import java.util.List;
import java.util.stream.Collectors;


public class ProdutoUseCase implements ProdutoUseCaseBoundary {

    private ProdutoGateway produtoGateway;

    public ProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ProdutoResponse buscarProdutoPorId(Long id) {
        try {
            Produto produto = this.produtoGateway.buscarPorId(id);

            if (produto != null)
                return ProdutoResponse.builder().id(produto.getId())
                        .nome(produto.getNome())
                        .descricao(produto.getDescricao())
                        .preco(produto.getPreco())
                        .categoria(produto.getCategoria())
                        .build();
            else
                return null;
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public List<ProdutoResponse> buscarTodosProdutos() {
        try {
            List<Produto> list = this.produtoGateway.listarTudo();
            return list.stream().map(c -> new ProdutoResponse(c.getId(), c.getNome(), c.getDescricao(), c.getCategoria(), c.getPreco())).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public List<ProdutoResponse> buscarProdutosPorCategoria(Categoria categoria) {
        try {
            List<Produto> list = this.produtoGateway.listarPorCategoria(categoria);
            return list.stream().map(c -> new ProdutoResponse(c.getId(), c.getNome(), c.getDescricao(), c.getCategoria(), c.getPreco())).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public ProdutoResponse inserirProduto(ProdutoPostRequest produtoRequest) throws FiapFoodException {
        try {
            Produto produto = Produto.builder()
                    .nome(produtoRequest.getNome())
                    .descricao(produtoRequest.getDescricao())
                    .preco(produtoRequest.getPreco())
                    .categoria(Categoria.builder().id(produtoRequest.getCategoriaId()).build())
                    .build();

            Produto produtoSaved = this.produtoGateway.inserir(produto);

            return ProdutoResponse.builder().id(produtoSaved.getId())
                    .nome(produtoSaved.getNome())
                    .descricao(produtoSaved.getDescricao())
                    .preco(produtoSaved.getPreco())
                    .categoria(produtoSaved.getCategoria())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public ProdutoResponse atualizar(ProdutoPutRequest produtoRequest) throws FiapFoodException {
        try {
            Produto produto = Produto.builder()
                    .id(produtoRequest.getId())
                    .nome(produtoRequest.getNome())
                    .descricao(produtoRequest.getDescricao())
                    .preco(produtoRequest.getPreco())
                    .categoria(Categoria.builder().id(produtoRequest.getCategoriaId()).build())
                    .build();

            Produto produtoSaved = this.produtoGateway.atualizar(produto);

            return ProdutoResponse.builder().id(produtoSaved.getId())
                    .nome(produtoSaved.getNome())
                    .descricao(produtoSaved.getDescricao())
                    .preco(produtoSaved.getPreco())
                    .categoria(produtoSaved.getCategoria())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    public Boolean excluir(Long id) throws FiapFoodException {
        try {
            return this.produtoGateway.excluir(id);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }
}
