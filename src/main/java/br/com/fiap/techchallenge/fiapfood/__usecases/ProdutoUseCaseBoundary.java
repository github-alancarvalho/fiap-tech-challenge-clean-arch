package br.com.fiap.techchallenge.fiapfood.__usecases;

import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;
import br.com.fiap.techchallenge.fiapfood._domain.valueobject.Cpf;

import java.util.List;

public interface ProdutoUseCaseBoundary {

    public ProdutoResponse inserirProduto(ProdutoPostRequest produtoRequest) throws FiapFoodException;

    public ProdutoResponse buscarProdutoPorId(Long id) throws FiapFoodException;

    public List<ProdutoResponse> buscarProdutosPorCategoria(Categoria categoria) throws FiapFoodException;

    public List<ProdutoResponse> buscarTodosProdutos() throws FiapFoodException;

    public ProdutoResponse atualizar(ProdutoPutRequest produtoRequest) throws FiapFoodException;

    public Boolean excluir(Long id) throws FiapFoodException;

}
