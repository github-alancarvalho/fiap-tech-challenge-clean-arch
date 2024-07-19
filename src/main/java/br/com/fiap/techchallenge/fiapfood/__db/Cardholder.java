package br.com.fiap.techchallenge.fiapfood.__db;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cardholder {

    private String name;
    private Identification identification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }
}
