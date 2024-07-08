package br.com.fiap.techchallenge.fiapfood._domain.valueobject;

import java.io.Serializable;

public class Telefone implements Serializable {

    private final String telefoneComleto;

    public Telefone(String numeroCompleto) {
        this.telefoneComleto = numeroCompleto;
    }

    public Telefone(Integer codigoDeArea, Integer numero) {
        this.telefoneComleto = String.valueOf(codigoDeArea) + String.valueOf(numero);
    }

    public String getTelefone() {
        return this.telefoneComleto;
    }
}

