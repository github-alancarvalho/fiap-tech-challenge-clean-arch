package br.com.fiap.techchallenge.fiapfood.external;

import br.com.fiap.techchallenge.fiapfood.__adapters.PagamentoPixResponse;
import br.com.fiap.techchallenge.fiapfood.external.mapper.PagamentoMapper;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.adapters.gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento2;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood.core.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood.core.entity.Pagamento;
import br.com.fiap.techchallenge.fiapfood.external.entities.Cardholder;
import br.com.fiap.techchallenge.fiapfood.external.entities.CartaoCreditoEntity;
import br.com.fiap.techchallenge.fiapfood.external.entities.Identification;
import br.com.fiap.techchallenge.fiapfood.external.entities.PagamentoEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class PagamentoGatewayDataMapper extends GenericDaoImpl<PagamentoEntity> implements PagamentoGateway {

    private String mercadoPagoAccessToken = System.getenv("TOKEN_MERCADO_PAGO");
    private static final String MERCADO_PAGO_API = "https://api.mercadopago.com/v1";
    private EntityManager entityManager;

    public PagamentoGatewayDataMapper() {
        setClazz(PagamentoEntity.class);
        this.entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        setEntityManager(this.entityManager);
    }

    @Override
    public Pagamento salvarDadosPagamento(Pagamento pagamento) {

        PagamentoEntity entity = PagamentoMapper.mapToEntity(pagamento);
        return PagamentoMapper.mapToEntity(save(entity));
    }

    @Override
    public Pagamento atualizarStatusPagamento(Pagamento pagamento, StatusPagamento status) {
        PagamentoEntity entity = PagamentoMapper.mapToEntity(buscarPagamentoPorId(pagamento.getId()));
        entity.setStatus(status);
        return PagamentoMapper.mapToEntity(update(entity));
    }

    @Override
    public Pagamento buscarPagamentoPorId(Long id) {
        PagamentoEntity entity = findById(id);
        return PagamentoMapper.mapToEntity(entity);
    }

    @Override
    public Pagamento buscarPagamentoPorIdPedido(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PagamentoEntity> criteriaQuery = criteriaBuilder.createQuery(PagamentoEntity.class);
        Root<PagamentoEntity> root = criteriaQuery.from(PagamentoEntity.class);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("idPedido"), id));

        PagamentoEntity result = entityManager.createQuery(criteriaQuery).getSingleResult();
        return PagamentoMapper.mapToEntity(result);

    }

    @Override
    public List<Pagamento> listarPagamentos() {
        return PagamentoMapper.mapListToEntity(findAll());
    }

    @Override
    public Pagamento2 efetuarPagamentoViaCartao(Pagamento2 pagamento2) {

        try {
            String token = gerarTokenCartaoCredito(pagamento2.getCartaoCredito());

            Payment pagamentoMP = getCreatedPayment(pagamento2, token);

            pagamento2.setStatus(pagamentoMP.getStatus());
            pagamento2.setIdPagamento(pagamentoMP.getId());
            return pagamento2;

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new FiapFoodException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new FiapFoodException(exception.getMessage());
        }
    }


    private Payment getCreatedPayment(Pagamento2 pagamento2, String token) throws MPException, MPApiException {
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
                        .notificationUrl(System.getenv("NOTIFICATION_MP_URL"))
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
        return createdPayment;
    }

    public String gerarTokenCartaoCredito(CartaoCredito cartaoCredito) {

        MercadoPagoConfig.setAccessToken(mercadoPagoAccessToken);
        try {

            RestTemplate restTemplate = new RestTemplate();

            Identification identification = Identification.builder().type("CPF").number(cartaoCredito.getCpf().getCpfSomenteNumero()).build();
            Cardholder cardHolder = Cardholder.builder().name("APRO").identification(identification).build();

            CartaoCreditoEntity entity = CartaoCreditoEntity.builder()
                    .card_number(cartaoCredito.getNumero())
                    .site_id("FiapFood2")
                    .expiration_month(String.valueOf(cartaoCredito.getMesVencimento()))
                    .expiration_year(String.valueOf(cartaoCredito.getAnoVencimento()))
                    .security_code(String.valueOf(cartaoCredito.getCvv()))
                    .cardholder(cardHolder)
                    .build();

            // Converter o objeto para JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(entity);

            // Configurar as headers da requisição
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(System.getenv("TOKEN_MERCADO_PAGO"));

            // Criar a entidade HTTP
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            // Fazer a requisição POST
            String url = "https://api.mercadopago.com/v1/card_tokens";
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response.getBody());

            String id = root.get("id").asText();

            System.out.println(response.getBody());
            return id;

        } catch (JsonProcessingException e) {
            throw new FiapFoodException(e.getMessage());
        }

    }

    @Override
    public String getDetalhesDoPagamento(Long paymentId) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = MERCADO_PAGO_API + "/payments/" + paymentId;
            // Configurar as headers da requisição
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(System.getenv("TOKEN_MERCADO_PAGO"));

            // Criar a entidade HTTP
            HttpEntity<String> request = new HttpEntity<>("", headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode root = objectMapper.readTree(response.getBody());

            String body = root.asText();

            System.out.println(response.getBody());
            System.out.println(body);
            return response.getBody().toString();

        } catch (JsonProcessingException e) {
            throw new FiapFoodException(e.getMessage());
        }
    }

    @Override
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

