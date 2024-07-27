package br.com.fiap.techchallenge.fiapfood.adapters.controllers;

import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.presenters.PagamentoPresenter;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PagamentoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.usecases.PedidoUseCaseBoundary;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPedido;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

//    public PagamentoResponse processarPagamento(PagamentoRequest pagamentoRequest, PagamentoPresenter pagamentoPresenter) {
//
//        try {
//
//            PagamentoResponse savedPagamento = this.pagamentoUseCaseBoundary.processarPagamento(pagamentoRequest);
//
//            if (savedPagamento != null)
//                return pagamentoPresenter.prepararRespostaComSucesso(savedPagamento);
//            else
//                return pagamentoPresenter.prepararRespostaSemSucesso(null);
//        } catch (FiapFoodException err) {
//            return pagamentoPresenter.prepararRespostaSemSucesso(null);
//        }
//    }

    public PagamentoResponse buscarPagamentoPorId(Long id, PagamentoPresenter pagamentoPresenter) {

        try {
            PagamentoResponse pagamento = this.pagamentoUseCaseBoundary.buscarPagamentoPorId(id);
            if (pagamento != null)
                return pagamentoPresenter.prepararRespostaComSucesso(pagamento);
            else
                return pagamentoPresenter.prepararRespostaVazia();

        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public PagamentoResponse buscarPagamentoPorIdPedido(Long id, PagamentoPresenter pagamentoPresenter) {

        try {
            PagamentoResponse pagamento = this.pagamentoUseCaseBoundary.buscarPagamentoPorIdPedido(id);
            if (pagamento != null)
                return pagamentoPresenter.prepararRespostaComSucesso(pagamento);
            else
                return pagamentoPresenter.prepararRespostaVazia();

        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento statusPagamento, PagamentoPresenter pagamentoPresenter) {

        try {

            PagamentoResponse pagamentoResponse = this.pagamentoUseCaseBoundary.atualizarPagamento(atualizarPagamentoRequest, statusPagamento);

            if (pagamentoResponse != null) {
                return pagamentoPresenter.prepararRespostaComSucesso(pagamentoResponse);
            } else {
                return pagamentoPresenter.prepararRespostaVazia();
            }
        } catch (FiapFoodException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(err);
        }
    }

    public String receberConfirmacaoDePagamento(Long idPagamento) {

        try {
            String detalhesPagamento = this.pagamentoUseCaseBoundary.getDetalhesDoPagamento(idPagamento);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(detalhesPagamento);

            String status = root.get("status").asText();


            AtualizarPagamentoRequest atualizarPagamentoRequest = AtualizarPagamentoRequest.builder().id(idPagamento).build();
            if (status.equals("approved")) {
                PagamentoResponse pagamentoResponse = this.pagamentoUseCaseBoundary.atualizarPagamento(atualizarPagamentoRequest, StatusPagamento.APROVADO);

                AlterarProgressoPedidoRequest alterarProgressoPedidoRequest = AlterarProgressoPedidoRequest.builder().id(pagamentoResponse.getIdPedido()).novoStatus(StatusPedido.RECEBIDO.toString()).build();
                PedidoResponse pedidoResponse = this.pedidoUseCaseBoundary.atualizarProgresso(alterarProgressoPedidoRequest);

                return pagamentoPresenter.prepararResposta(pedidoResponse);
            } else
                return "Problema para atualizar o status do pedido";
        } catch (JsonProcessingException err) {
            return pagamentoPresenter.prepararRespostaSemSucesso(err);
        }

    }
}
