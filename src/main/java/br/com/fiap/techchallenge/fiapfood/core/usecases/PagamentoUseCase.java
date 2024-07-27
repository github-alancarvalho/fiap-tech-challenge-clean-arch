package br.com.fiap.techchallenge.fiapfood.core.usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.AtualizarPagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoPixResponse;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoRequest;
import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoResponse;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.*;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;

import java.util.List;
import java.util.stream.Collectors;


public class PagamentoUseCase implements PagamentoUseCaseBoundary {

    private PagamentoGateway pagamentoGateway;

    public PagamentoUseCase(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public PagamentoResponse buscarPagamentoPorId(Long id) {
        try {
            Pagamento pagamento = this.pagamentoGateway.buscarPagamentoPorId(id);


            if (pagamento != null)
                return PagamentoResponse.builder().id(pagamento.getId())
                        .status(pagamento.getStatus().toString())
                        .valor(pagamento.getValor())
                        .idPedido(pagamento.getIdPedido())
                        .build();
            else
                return null;
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PagamentoResponse buscarPagamentoPorIdPedido(Long id) {
        try {
            Pagamento pagamento = this.pagamentoGateway.buscarPagamentoPorIdPedido(id);

            if (pagamento != null)
                return PagamentoResponse.builder().id(pagamento.getId())
                        .status(pagamento.getStatus().toString())
                        .valor(pagamento.getValor())
                        .idPedido(pagamento.getIdPedido())
                        .build();
            else
                return null;
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public List<PagamentoResponse> buscarTodosPagamentos() {
        try {
            List<Pagamento> list = this.pagamentoGateway.listarPagamentos();
            return list.stream().map(c -> PagamentoResponse.builder().id(c.getId()).idPedido(c.getIdPedido()).status(c.getStatus().toString()).valor(c.getValor()).build()).collect(Collectors.toList());
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public PagamentoResponse salvarDadosPagamento(PagamentoRequest pagamentoRequest) {
        try {
            Pagamento pagamento = Pagamento.builder()
                    .id(pagamentoRequest.getId())
                    .status(StatusPagamento.EM_PROCESSAMENTO)
                    .valor(pagamentoRequest.getValor())
                    .idPedido(pagamentoRequest.getIdPedido())
                    .build();

            Pagamento pagamentoSaved = this.pagamentoGateway.salvarDadosPagamento(pagamento);

            return PagamentoResponse.builder().id(pagamentoSaved.getId())
                    .status(pagamentoSaved.getStatus().toString())
                    .valor(pagamentoSaved.getValor())
                    .idPedido(pagamentoSaved.getIdPedido())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }


    @Override
    public PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento status) throws FiapFoodException {
        try {
            PagamentoResponse pagamentoResponse;
            if (atualizarPagamentoRequest.getId() != null) {
                pagamentoResponse = buscarPagamentoPorId(atualizarPagamentoRequest.getId());

            } else if (atualizarPagamentoRequest.getIdPedido() != null) {
                pagamentoResponse = buscarPagamentoPorIdPedido(atualizarPagamentoRequest.getIdPedido());
            } else
                return null;

            Pagamento pagamento = Pagamento.builder()
                    .id(pagamentoResponse.getId())
                    .status(status)
                    .idPedido(pagamentoResponse.getIdPedido())
                    .valor(pagamentoResponse.getValor())
                    .build();


            Pagamento pagamentoSaved = this.pagamentoGateway.atualizarStatusPagamento(pagamento, status);

            return PagamentoResponse.builder()
                    .id(pagamentoSaved.getId())
                    .status(pagamentoSaved.getStatus().toString())
                    .valor(pagamentoSaved.getValor())
                    .idPedido(pagamentoSaved.getIdPedido())
                    .build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public Pagamento2 prepararPagamento(Cliente cliente, Pedido pedido, Double valorTotalPedido, CartaoCredito cartaoCredito) {
        try {
            return Pagamento2.builder().cliente(cliente).pedido(pedido).valor(valorTotalPedido).cartaoCredito(cartaoCredito).build();
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }


    @Override
    public PagamentoResponse efetuarPagamentoViaCartao(Pagamento2 pagamento2) {
        try {

            Pagamento2 pagamentoEnviado = this.pagamentoGateway.efetuarPagamentoViaCartao(pagamento2);

            salvarDadosPagamento(PagamentoRequest.builder().id(pagamentoEnviado.getIdPagamento()).idPedido(pagamento2.getPedido().getId()).valor(pagamento2.getValor()).build());

            return PagamentoResponse.builder().
                    id(pagamentoEnviado.getIdPagamento())
                    .idPedido(pagamento2.getPedido().getId())
                    .status(String.valueOf(pagamentoEnviado.getStatus()))
                    .valor(pagamento2.getValor()).build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new FiapFoodException(e.getMessage());
        }
    }

    @Override
    public PagamentoPixResponse efetuarPagamentoViaPix(Pagamento2 pagamento2) {
        try {
            return this.pagamentoGateway.efetuarPagamentoViaPix(pagamento2);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }

    @Override
    public String getDetalhesDoPagamento(Long idDoPagamento) {
        try {
            return this.pagamentoGateway.getDetalhesDoPagamento(idDoPagamento);
        } catch (Exception err) {
            throw new FiapFoodException(err.getMessage());
        }
    }
}



