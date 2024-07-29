package br.com.fiap.techchallenge.fiapfood.adapters.controllers;

import br.com.fiap.techchallenge.fiapfood.dto.*;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoPresenter;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PedidoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PedidoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.usecases.ProdutoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    private PedidoUseCaseBoundary pedidoUseCaseBoundary;
    private PagamentoUseCaseBoundary pagamentoUseCaseBoundary;
    private ProdutoUseCaseBoundary produtoUseCaseBoundary;

    public PedidoController(PedidoUseCaseBoundary pedidoUseCaseBoundary, ProdutoUseCaseBoundary produtoUseCaseBoundary, PagamentoUseCaseBoundary pagamentoUseCaseBoundary) {
        this.pedidoUseCaseBoundary = pedidoUseCaseBoundary;
        this.pagamentoUseCaseBoundary = pagamentoUseCaseBoundary;
        this.produtoUseCaseBoundary = produtoUseCaseBoundary;
    }

    public List<PedidoResponse> buscarTodosPedidos(PedidoPresenter pedidoPresenter) {

        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarTodosPedidos();
            return pedidoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

//    public PedidoResponse inserir(PedidoRequest pedidoRequest, PedidoPresenter pedidoPresenter) {
//
//        try {
//
//            PedidoResponse savedPedido = this.pedidoUseCaseBoundary.inserirPedido(pedidoRequest);
//
//            if (savedPedido != null)
//                return savedPedido;
//            else
//                return null;
//        } catch (FiapFoodException err) {
//            return null;
//        }
//    }

    public PedidoResponse buscarPedidoPorId(Long id, PedidoPresenter pedidoPresenter) {

        try {
            PedidoResponse pedido = this.pedidoUseCaseBoundary.buscarPedidoPorId(id);
            if (pedido != null)
                return pedidoPresenter.prepararRespostaComSucesso(pedido);
            else
                return pedidoPresenter.prepararRespostaVazia();

        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public PedidoResponse alterarProgressoPedido(AlterarProgressoPedidoRequest alterarProgressoPedidoRequest, PedidoPresenter pedidoPresenter) {

        try {

            PedidoResponse pedidoResponse = this.pedidoUseCaseBoundary.atualizarProgresso(alterarProgressoPedidoRequest);

            return pedidoPresenter.prepararRespostaComSucesso(pedidoResponse);
        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public Boolean excluir(Long id, PedidoPresenter pedidoPresenter) {

        try {
            return this.pedidoUseCaseBoundary.excluir(id);
        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaComErroExcluir(err);
        }
    }

    public List<PedidoResponse> buscarPedidosPorStatus(StatusPedido statusPedido, PedidoPresenter pedidoPresenter) {

        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosPorStatus(statusPedido);
            return pedidoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }


    public List<PedidoResponse> buscarPedidosEmAberto(PedidoPresenter pedidoPresenter) {
        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosEmAberto();
            return pedidoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

    public List<PedidoResponse> buscarPedidosAguardandoPagamento(PedidoPresenter pedidoPresenter) {
        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosAguardandoPagamento();
            return pedidoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return pedidoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }


    public PagamentoResponse checkout(PedidoRequest pedidoRequest, PagamentoPresenter pagamentoPresenter) {

        try {

            PagamentoResponse pagamentoResponse = this.pedidoUseCaseBoundary.checkout(pedidoRequest);

//            PedidoResponse savedPedido = this.pedidoUseCaseBoundary.inserirPedido(pedidoRequest);
//
//            for(ItemPedido itemPedido: savedPedido.getListItens()){
//                ProdutoResponse produtoResponse = produtoUseCaseBoundary.buscarProdutoPorId(itemPedido.getProduto().getId());
//                itemPedido.setProduto(ProdutoMapper.mapToEntity(produtoResponse));
//            }
//            pedidoRequest.setListItens(savedPedido.getListItens());
//
//            Double valorTotalPedido = this.pedidoUseCaseBoundary.calcularValorTotalDoPedido(pedidoRequest);
//
//            PagamentoRequest pagamentoRequest = PagamentoRequest.builder()
//                    .idPedido(savedPedido.getId())
//                    .valor(valorTotalPedido).build();
//
//            //this.pagamentoUseCaseBoundary.prepararPagamento()
//
//            this.pagamentoUseCaseBoundary.processarPagamento(pagamentoRequest);


//            if (savedPedido != null)
//                return pedidoPresenter.prepararRespostaComSucesso(savedPedido);
//            else
//                return null;

            return pagamentoPresenter.prepararRespostaComSucesso(pagamentoResponse);
        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(err);
        }
    }
}
