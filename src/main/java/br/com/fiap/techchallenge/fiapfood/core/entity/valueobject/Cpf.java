package br.com.fiap.techchallenge.fiapfood.core.entity.valueobject;

import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Cpf implements Serializable {

    private static final String CPF_INVALIDO = "CPF inválido.";

    @NotNull
    @Column(name = "cpf", nullable = false)
    private final String cpfCompleto;

    public Cpf(String cpf) {
        // Remove caracteres especiais e espaços em branco
        this.cpfCompleto = cpf.replaceAll("[\\D]", "");
        validarCPF(this.cpfCompleto);
    }

    public String getCpfSomenteNumero() {
        return cpfCompleto;
    }

    private void validarCPF(String cpf) {
        if (cpf.length() != 11) {
            throw new FiapFoodException("CPF deve conter 11 dígitos.");
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            throw new FiapFoodException(CPF_INVALIDO);
        }

        // Calcula e verifica o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        if (Character.getNumericValue(cpf.charAt(9)) != digito1) {
            throw new FiapFoodException(CPF_INVALIDO);
        }

        // Calcula e verifica o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        if (Character.getNumericValue(cpf.charAt(10)) != digito2) {
            throw new FiapFoodException(CPF_INVALIDO);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpfObj = (Cpf) o;
        return Objects.equals(cpfCompleto, cpfObj.cpfCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfCompleto);
    }

    @Override
    public String toString() {
        return cpfCompleto;
    }

}

