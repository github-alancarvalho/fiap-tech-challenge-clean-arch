package br.com.fiap.techchallenge.fiapfood.core.usecases;

import br.com.fiap.techchallenge.fiapfood.dto.ProdutoPostRequest;
import br.com.fiap.techchallenge.fiapfood.dto.ProdutoPutRequest;
import br.com.fiap.techchallenge.fiapfood.dto.ProdutoResponse;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;

import java.util.List;

public interface ProdutoUseCaseBoundary {

    public ProdutoResponse inserirProduto(ProdutoPostRequest produtoRequest) throws FiapFoodException;

    public ProdutoResponse buscarProdutoPorId(Long id) throws FiapFoodException;

    public List<ProdutoResponse> buscarProdutosPorCategoria(Categoria categoria) throws FiapFoodException;

    public List<ProdutoResponse> buscarTodosProdutos() throws FiapFoodException;

    public ProdutoResponse atualizar(ProdutoPutRequest produtoRequest) throws FiapFoodException;

    public Boolean excluir(Long id) throws FiapFoodException;

}
