package br.com.fiap.techchallenge.fiapfood.__controller;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__db.mapper.ProdutoMapper;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__presenters.PedidoPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.__usecases.PedidoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.__usecases.ProdutoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.ItemPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Produto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoController {

    private PedidoUseCaseBoundary pedidoUseCaseBoundary;
    private PagamentoUseCaseBoundary pagamentoUseCaseBoundary;
    private ProdutoUseCaseBoundary produtoUseCaseBoundary;

    public PedidoController(PedidoUseCaseBoundary pedidoUseCaseBoundary, ProdutoUseCaseBoundary produtoUseCaseBoundary, PagamentoUseCaseBoundary pagamentoUseCaseBoundary) {
        this.pedidoUseCaseBoundary = pedidoUseCaseBoundary;
        this.pagamentoUseCaseBoundary = pagamentoUseCaseBoundary;
        this.produtoUseCaseBoundary = produtoUseCaseBoundary;
    }

    public List<PedidoResponse> buscarTodosPedidos() {

        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarTodosPedidos();
            return list;

        } catch (FiapFoodException err) {
            return new ArrayList<>();
        }
    }

    public PedidoResponse inserir(PedidoRequest pedidoRequest) {

        try {

            PedidoResponse savedPedido = this.pedidoUseCaseBoundary.inserirPedido(pedidoRequest);

            if (savedPedido != null)
                return savedPedido;
            else
                return null;
        } catch (FiapFoodException err) {
            return null;
        }
    }

    public PedidoResponse buscarPedidoPorId(Long id) {

        try {
            PedidoResponse pedido = this.pedidoUseCaseBoundary.buscarPedidoPorId(id);
            if (pedido != null)
                return pedido;
            else
                return null;

        } catch (FiapFoodException err) {
            return null;
        }
    }

    public PedidoResponse alterarProgressoPedido(AlterarProgressoPedidoRequest alterarProgressoPedidoRequest) {

        try {

            PedidoResponse pedidoResponse = this.pedidoUseCaseBoundary.atualizarProgresso(alterarProgressoPedidoRequest);

            return pedidoResponse;
        } catch (FiapFoodException err) {
            return null;
        }
    }

    public Boolean excluir(Long id) {

        try {
            return this.pedidoUseCaseBoundary.excluir(id);
        } catch (FiapFoodException err) {
            return false;
        }
    }

    public List<PedidoResponse> buscarPedidosPorStatus(StatusPedido statusPedido) {

        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosPorStatus(statusPedido);
            return list;

        } catch (FiapFoodException err) {
            return new ArrayList<>();
        }
    }


    public List<PedidoResponse> buscarPedidosEmAberto() {
        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosEmAberto();
            return list;

        } catch (FiapFoodException err) {
            return new ArrayList<>();
        }
    }

    public List<PedidoResponse> buscarPedidosAguardandoPagamento() {
        List<PedidoResponse> list = new ArrayList<>();
        try {
            list = this.pedidoUseCaseBoundary.buscarPedidosAguardandoPagamento();
            return list;

        } catch (FiapFoodException err) {
            return new ArrayList<>();
        }
    }


    public PagamentoResponse checkout(PedidoRequest pedidoRequest) {

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

            return pagamentoResponse;
        } catch (FiapFoodException err) {
            return null;
        }
    }
}
