package br.com.fiap.techchallenge.fiapfood.__controller;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__presenters.PagamentoPresenter;
import br.com.fiap.techchallenge.fiapfood.__usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.__usecases.PedidoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class PagamentoController {

    private PagamentoUseCaseBoundary pagamentoUseCaseBoundary;
    private PedidoUseCaseBoundary pedidoUseCaseBoundary;
    private PagamentoPresenter pagamentoPresenter;


    public PagamentoController(PagamentoUseCaseBoundary pagamentoUseCaseBoundary, PedidoUseCaseBoundary pedidoUseCaseBoundary) {
        this.pagamentoUseCaseBoundary = pagamentoUseCaseBoundary;
        this.pedidoUseCaseBoundary = pedidoUseCaseBoundary;
    }

    public List<PagamentoResponse> buscarTodosPagamentos(PagamentoPresenter pagamentoPresenter) {

        List<PagamentoResponse> list = new ArrayList<>();
        try {
            list = this.pagamentoUseCaseBoundary.buscarTodosPagamentos();
            return pagamentoPresenter.prepararRespostaListaComSucesso(list);

        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaListaSemSucesso(err);
        }
    }

    public PagamentoResponse processarPagamento(PagamentoRequest pagamentoRequest, PagamentoPresenter pagamentoPresenter) {

        try {

            PagamentoResponse savedPagamento = this.pagamentoUseCaseBoundary.processarPagamento(pagamentoRequest);

            if (savedPagamento != null)
                return pagamentoPresenter.prepararRespostaComSucesso(savedPagamento);
            else
                return pagamentoPresenter.prepararRespostaSemSucesso(null);
        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(null);
        }
    }

    public PagamentoResponse buscarPagamentoPorId(Long id, PagamentoPresenter pagamentoPresenter) {

        try {
            PagamentoResponse pagamento = this.pagamentoUseCaseBoundary.buscarPagamentoPorId(id);
            if (pagamento != null)
                return pagamentoPresenter.prepararRespostaComSucesso(pagamento);
            else
                return pagamentoPresenter.prepararRespostaSemSucesso(null);

        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(null);
        }
    }

    public PagamentoResponse buscarPagamentoPorIdPedido(Long id, PagamentoPresenter pagamentoPresenter) {

        try {
            PagamentoResponse pagamento = this.pagamentoUseCaseBoundary.buscarPagamentoPorIdPedido(id);
            if (pagamento != null)
                return pagamentoPresenter.prepararRespostaComSucesso(pagamento);
            else
                return pagamentoPresenter.prepararRespostaSemSucesso(null);

        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(null);
        }
    }

    public PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento statusPagamento, PagamentoPresenter pagamentoPresenter) {

        try {

            PagamentoResponse pagamentoResponse = this.pagamentoUseCaseBoundary.atualizarPagamento(atualizarPagamentoRequest, statusPagamento);

            if (pagamentoResponse != null) {
                return pagamentoPresenter.prepararRespostaComSucesso(pagamentoResponse);
            } else {
                return pagamentoPresenter.prepararRespostaSemSucesso(null);
            }
        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(null);
        }
    }

    public String receberConfirmacaoDePagamento(Long idPagamento) {
        AtualizarPagamentoRequest atualizarPagamentoRequest = AtualizarPagamentoRequest.builder().id(idPagamento).build();
        PagamentoResponse pagamentoResponse = this.pagamentoUseCaseBoundary.atualizarPagamento(atualizarPagamentoRequest, StatusPagamento.APROVADO);

//        PagamentoResponse pagamentoResponse = this.pagamentoUseCaseBoundary.receberConfirmacaoDePagamento(idPagamento);

        AlterarProgressoPedidoRequest alterarProgressoPedidoRequest = AlterarProgressoPedidoRequest.builder().id(pagamentoResponse.getIdPedido()).novoStatus(StatusPedido.RECEBIDO.toString()).build();
        PedidoResponse pedidoResponse = this.pedidoUseCaseBoundary.atualizarProgresso(alterarProgressoPedidoRequest);

        if (pedidoResponse != null)
            return "Pedido recebido com sucesso";
        else
            return "Problema para atualizar o status do pedido";

    }
}
