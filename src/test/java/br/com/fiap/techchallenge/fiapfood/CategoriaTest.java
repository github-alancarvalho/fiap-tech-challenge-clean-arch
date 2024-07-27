package br.com.fiap.techchallenge.fiapfood;


import br.com.fiap.techchallenge.fiapfood.core.ICategoria;
import br.com.fiap.techchallenge.fiapfood.core.entity.Categoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaTest {

    @Test
    public void test_givenAbcNome_whenNomeIsValid_thenIsTrue(){
        ICategoria categoria = new Categoria(null, "Lanche top", "Lanche com tudo dentro");
        assertTrue(categoria.isNomeValido());
    }

    @Test
    public void test_givenAbcNome_whenNomeIsNotValid_thenIsFalse(){
        ICategoria categoria = new Categoria(null, "err", "Lanche com tudo dentro");
        assertFalse(categoria.isNomeValido());
    }

    @Test
    public void test_givenAbcDescricao_whenDescricaoIsValid_thenIsTrue(){
        ICategoria categoria = new Categoria(null, "Lanche top", "Lanche com tudo dentro");
        assertTrue(categoria.isDescricaoValida());
    }

    @Test
    public void test_givenAbcDescricao_whenDescricaoIsNotValid_thenIsFalse(){
        ICategoria categoria = new Categoria(null, "Lanche top", "123");
        assertFalse(categoria.isDescricaoValida());
    }

}
