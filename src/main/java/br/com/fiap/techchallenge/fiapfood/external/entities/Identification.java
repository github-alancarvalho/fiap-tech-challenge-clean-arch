package br.com.fiap.techchallenge.fiapfood.external.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Identification {

    private String type;
    private String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
