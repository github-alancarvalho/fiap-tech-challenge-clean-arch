package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.exceptions.FiapFoodException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CpfTest {

    @Test
    public void shouldCreateValidCpf() {
        Cpf cpf = new Cpf("29527833060");
        assertEquals("29527833060",cpf.getCpfSomenteNumero());
    }

    @Test
    public void shouldThrowExceptionForInvalidCpfLength() {
        assertThrows(FiapFoodException.class, () -> new Cpf("123456789"));
        assertThrows(FiapFoodException.class, () -> new Cpf("123.456.789-000"));
    }

    @Test
    public void shouldThrowExceptionForInvalidCpfFormat() {
        assertThrows(FiapFoodException.class, () -> new Cpf("123.456.A89-00"));
        assertThrows(FiapFoodException.class, () -> new Cpf("123456-78900"));
    }

    @Test
    public void shouldThrowExceptionForNullCpf() {
        assertThrows(NullPointerException.class, () -> new Cpf(null));
    }

    @Test
    public void shouldThrowExceptionForEmptyCpf() {
        assertThrows(FiapFoodException.class, () -> new Cpf(""));
    }

}
