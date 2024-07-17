package br.com.fiap.techchallenge.fiapfood.__gateways;

import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;

import java.util.List;
import java.util.Optional;

public interface PagamentoGateway {


    Pagamento processarPagamento(Pagamento pagamento);

    Pagamento atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status);

    Pagamento buscarPagamentoPorId(Long id);

    Pagamento buscarPagamentoPorIdPedido(Long id);

    List<Pagamento> listarPagamentos();

    String gerarTokenCartaoCredito(CartaoCredito cartaoCredito);
}