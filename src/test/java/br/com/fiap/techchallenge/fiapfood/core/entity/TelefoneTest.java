package br.com.fiap.techchallenge.fiapfood.core.entity;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Telefone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TelefoneTest {

    @Test
    public void shouldCreateTelefoneFromCompleteNumber() {
        Telefone telefone = new Telefone("11987654321");
        assertEquals("11987654321", telefone.getTelefone());
    }

    @Test
    public void shouldCreateTelefoneFromAreaCodeAndNumber() {
        Telefone telefone = new Telefone(11, 987654321);
        assertEquals("11987654321", telefone.getTelefone());
    }

    @Test
    public void shouldThrowExceptionForNullTelefone() {
        assertThrows(NullPointerException.class, () -> new Telefone(null));
    }

    @Test
    public void shouldThrowExceptionForEmptyTelefone() {
        assertThrows(IllegalArgumentException.class, () -> new Telefone(""));
    }

    @Test
    public void shouldThrowExceptionForInvalidTelefoneFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Telefone("11-98765-4321"));
    }

    // Testar telefones com diferentes formatos (com e sem hífen, com parênteses, etc.)
    // Testar telefones com caracteres especiais
}
