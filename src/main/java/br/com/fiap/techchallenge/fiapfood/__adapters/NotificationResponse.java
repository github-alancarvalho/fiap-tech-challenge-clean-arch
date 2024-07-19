package br.com.fiap.techchallenge.fiapfood.__adapters;

import com.fasterxml.jackson.annotation.JsonProperty;

public record NotificationResponse(
        @JsonProperty("id") Long id
) {
}