package br.com.fiap.techchallenge.fiapfood;


import br.com.fiap.techchallenge.fiapfood._domain.ICategoria;
import br.com.fiap.techchallenge.fiapfood._domain.ICategoriaFactory;
import br.com.fiap.techchallenge.fiapfood._domain.factory.CategoriaFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaFactoryTest {

    @Test
    public void test_givenAbcNome_whenNomeIsValid_thenIsTrue(){
        ICategoriaFactory factory = new CategoriaFactory();
        ICategoria categoria = factory.create(null, "Lanche top", "Lanche com tudo dentro");
        assertTrue(categoria.isNomeValido());
    }

    @Test
    public void test_givenAbcNome_whenNomeIsNotValid_thenIsFalse(){
        ICategoriaFactory factory = new CategoriaFactory();
        ICategoria categoria = factory.create(null, "err", "Lanche com tudo dentro");
        assertFalse(categoria.isNomeValido());
    }

    @Test
    public void test_givenAbcDescricao_whenDescricaoIsValid_thenIsTrue(){
        ICategoriaFactory factory = new CategoriaFactory();
        ICategoria categoria = factory.create(null, "Lanche top", "Lanche com tudo dentro");
        assertTrue(categoria.isDescricaoValida());
    }

    @Test
    public void test_givenAbcDescricao_whenDescricaoIsNotValid_thenIsFalse(){
        ICategoriaFactory factory = new CategoriaFactory();
        ICategoria categoria = factory.create(null, "Lanche top", "123");
        assertFalse(categoria.isDescricaoValida());
    }

}
