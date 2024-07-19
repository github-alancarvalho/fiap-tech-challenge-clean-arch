package br.com.fiap.techchallenge.fiapfood.__db;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Teste {

    public static void main(String args[]) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Identification identification = Identification.builder().type("CPF").number("12345678909").build();
        Cardholder cardHolder = Cardholder.builder().name("APRO").identification(identification).build();

        CartaoCreditoEntity entity = CartaoCreditoEntity.builder()
                .card_number("4235647728025682")
                .site_id("FiapFood2")
                .expiration_month(String.valueOf("11"))
                .expiration_year(String.valueOf("2025"))
                .security_code(String.valueOf("123"))
                .cardholder(cardHolder)
                .build();

            // Converter o objeto para JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(entity);

            // Configurar as headers da requisição
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth("TEST-1882441822874146-071123-2bd1432e98f1efc3e1ff3a4ae6ecd79e-1229643287");

            // Criar a entidade HTTP
            HttpEntity<String> request = new HttpEntity<>(json, headers);

            // Fazer a requisição POST
            String url = "https://api.mercadopago.com/v1/card_tokens";
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            System.out.println(response.getBody());
            response.getBody();


    }

}
