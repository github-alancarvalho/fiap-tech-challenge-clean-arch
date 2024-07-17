package br.com.fiap.techchallenge.fiapfood.__db;

import br.com.fiap.techchallenge.fiapfood.__db.mapper.PagamentoMapper;
import br.com.fiap.techchallenge.fiapfood.__exceptions.FiapFoodException;
import br.com.fiap.techchallenge.fiapfood.__gateways.PagamentoGateway;
import br.com.fiap.techchallenge.fiapfood._domain.base.StatusPagamento;
import br.com.fiap.techchallenge.fiapfood._domain.entity.CartaoCredito;
import br.com.fiap.techchallenge.fiapfood._domain.entity.Pagamento;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class PagamentoGatewayDataMapper extends GenericDaoImpl<PagamentoEntity> implements PagamentoGateway {

    private EntityManager entityManager;

    public PagamentoGatewayDataMapper() {
        setClazz(PagamentoEntity.class);
        this.entityManager = (new ConnectionPoolManager()).getConnection().createEntityManager();
        setEntityManager(this.entityManager);
    }

    @Override
    public Pagamento processarPagamento(Pagamento pagamento) {

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
    public String gerarTokenCartaoCredito(CartaoCredito cartaoCredito) {

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
}

