package br.com.fiap.techchallenge.fiapfood.__usecases;


import br.com.fiap.techchallenge.fiapfood.__adapters.*;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPedido;
import br.com.fiap.techchallenge.fiapfood._domain.entity.*;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class PagamentoUseCase implements PagamentoUseCaseBoundary {

    private PagamentoGateway pagamentoGateway;

    //    @Value("${mercado_pago_sample_access_token}")
    private String mercadoPagoAccessToken = System.getenv("TOKEN_MERCADO_PAGO");

    public PagamentoUseCase(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    @Override
    public PagamentoResponse buscarPagamentoPorId(Long id) {
        Pagamento pagamento = this.pagamentoGateway.buscarPagamentoPorId(id);

        if (pagamento != null)
            return PagamentoResponse.builder().id(pagamento.getId())
                    .status(pagamento.getStatus().toString())
                    .valor(pagamento.getValor())
                    .idPedido(pagamento.getIdPedido())
                    .build();
        else
            return null;
    }

    @Override
    public PagamentoResponse buscarPagamentoPorIdPedido(Long id) {
        Pagamento pagamento = this.pagamentoGateway.buscarPagamentoPorIdPedido(id);

        if (pagamento != null)
            return PagamentoResponse.builder().id(pagamento.getId())
                    .status(pagamento.getStatus().toString())
                    .valor(pagamento.getValor())
                    .idPedido(pagamento.getIdPedido())
                    .build();
        else
            return null;
    }

    @Override
    public List<PagamentoResponse> buscarTodosPagamentos() {
        List<Pagamento> list = this.pagamentoGateway.listarPagamentos();
        return list.stream().map(c -> PagamentoResponse.builder().id(c.getId()).idPedido(c.getIdPedido()).status(c.getStatus().toString()).valor(c.getValor()).build()).collect(Collectors.toList());
    }

    @Override
    public PagamentoResponse processarPagamento(PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = Pagamento.builder()
                .id(pagamentoRequest.getId())
                .status(StatusPagamento.EM_PROCESSAMENTO)
                .valor(pagamentoRequest.getValor())
                .idPedido(pagamentoRequest.getIdPedido())
                .build();

        Pagamento pagamentoSaved = this.pagamentoGateway.processarPagamento(pagamento);

        return PagamentoResponse.builder().id(pagamentoSaved.getId())
                .status(pagamentoSaved.getStatus().toString())
                .valor(pagamentoSaved.getValor())
                .idPedido(pagamentoSaved.getIdPedido())
                .build();
    }

//    @Override
//    public PagamentoResponse receberConfirmacaoDePagamento(Long idPagamento) {
//
//        AtualizarPagamentoRequest atualizarPagamentoRequest = AtualizarPagamentoRequest.builder().id(idPagamento).build();
//         return atualizarPagamento(atualizarPagamentoRequest, StatusPagamento.APROVADO);
//    }

    @Override
    public PagamentoResponse atualizarPagamento(AtualizarPagamentoRequest atualizarPagamentoRequest, StatusPagamento status) throws FiapFoodException {

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
    }

    @Override
    public Pagamento2 prepararPagamento(Cliente cliente, Pedido pedido, Double valorTotalPedido, CartaoCredito cartaoCredito) {

        return Pagamento2.builder().cliente(cliente).pedido(pedido).valor(valorTotalPedido).cartaoCredito(cartaoCredito).build();
    }

    @Override
    public String gerarTokenCartaoCredito(CartaoCredito cartaoCredito) {
        return this.pagamentoGateway.gerarTokenCartaoCredito(cartaoCredito);
    }

    @Override
    public PagamentoResponse efetuarPagamento(Pagamento2 pagamento2) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);


            String token = gerarTokenCartaoCredito(pagamento2.getCartaoCredito());

            PaymentClient paymentClient = new PaymentClient();

            String resumoItens = pagamento2.getPedido().getListItens().stream()
                    .map(itemPedido -> String.format("Item %s - Quantidade: %s - Valor: %s",
                            itemPedido.getProduto().getNome(), itemPedido.getQuantidade(),
                            itemPedido.getProduto().getPreco())).collect(Collectors.joining("\n"));


            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(BigDecimal.valueOf(pagamento2.getValor()))
                            .description(resumoItens)
                            .paymentMethodId("visa")
                            .token(token)
                            .installments(1)
//                            .notificationUrl(System.getenv("NOTIFICATION_MP_URL"))
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(pagamento2.getCliente().getEmail())
                                            .firstName(pagamento2.getCliente().getNome())
                                            .lastName("")
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type("CPF")
                                                            .number(pagamento2.getCliente().getCpf().getCpfSomenteNumero())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            System.out.println("Payment id: " + createdPayment.getId());
            System.out.println("Pedido id: " + pagamento2.getPedido().getId());
            System.out.println("Valor: " + pagamento2.getValor());

            processarPagamento(PagamentoRequest.builder().id(createdPayment.getId()).idPedido(pagamento2.getPedido().getId()).valor(pagamento2.getValor()).build());

            return PagamentoResponse.builder().
                    id(createdPayment.getId())
                    .idPedido(pagamento2.getPedido().getId())
                    .status(String.valueOf(createdPayment.getStatus()))
                    .valor(pagamento2.getValor()).build();


        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new FiapFoodException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new FiapFoodException(exception.getMessage());
        }
    }
    public PagamentoPixResponse efetuarPagamentoViaPix(Pagamento2 pagamento2) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);


            String token = gerarTokenCartaoCredito(pagamento2.getCartaoCredito());

            PaymentClient paymentClient = new PaymentClient();

            String resumoItens = pagamento2.getPedido().getListItens().stream()
                    .map(itemPedido -> String.format("Item %s - Quantidade: %s - Valor: %s",
                            itemPedido.getProduto().getNome(), itemPedido.getQuantidade(),
                            itemPedido.getProduto().getPreco())).collect(Collectors.joining("\n"));


            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(BigDecimal.valueOf(pagamento2.getValor()))
                            .description(resumoItens)
                            .paymentMethodId("pix")
//                            .paymentMethodId("visa")
//                            .token("ba559a511b6076bbab3c05128038fe97")
//                            .token(token)
                            .installments(1)
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(pagamento2.getCliente().getEmail())
                                            .firstName(pagamento2.getCliente().getNome())
                                            .lastName("")
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type("CPF")
                                                            .number(pagamento2.getCliente().getCpf().getCpfSomenteNumero())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            return new PagamentoPixResponse(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getTransactionDetails().toString(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new FiapFoodException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new FiapFoodException(exception.getMessage());
        }
    }

}



