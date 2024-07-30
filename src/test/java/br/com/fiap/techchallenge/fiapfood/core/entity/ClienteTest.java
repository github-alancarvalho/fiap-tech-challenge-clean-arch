package br.com.fiap.techchallenge.fiapfood.core.entity;

import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Cpf;
import br.com.fiap.techchallenge.fiapfood.core.entity.valueobject.Telefone;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void deveCriarClienteValido() {
        // Dados de teste
        Cpf cpfValido = new Cpf("29527833060");
        String nome = "Cliente da Silva";
        String email = "cliente@email.com";
        Telefone telefone = new Telefone("11987654321");

        // Cria o cliente
        Cliente cliente = new Cliente(cpfValido, nome, email, telefone);

        // Asserções
        assertEquals(cpfValido, cliente.getCpf());
        assertEquals(nome, cliente.getNome());
        assertEquals(email, cliente.getEmail());
        assertEquals(telefone.getTelefone(), cliente.getTelefone().getTelefone());
    }

    @Test
    public void deveVerificarIgualdadeDeClientes() {
        // Dados de teste
        Cpf cpf = new Cpf("29527833060");
        String nome = "Cliente da Silva";
        String email = "cliente@email.com";
        Telefone telefone = new Telefone("11998765432");

        Cliente cliente1 = new Cliente(cpf, nome, email, telefone);
        Cliente cliente2 = new Cliente(cpf, nome, email, telefone);

        // Asserção
        assertTrue(cliente1.equals(cliente2));
    }

    @Test
    public void naoDeveVerificarIgualdadeDeClientesComCpfDiferente() {
        // Dados de teste
        Cpf cpf1 = new Cpf("29527833060");
        Cpf cpf2 = new Cpf("78456513032");
        String nome = "Cliente da Silva";
        String email = "cliente@email.com";
        Telefone telefone = new Telefone("11998765432");

        Cliente cliente1 = new Cliente(cpf1, nome, email, telefone);
        Cliente cliente2 = new Cliente(cpf2, nome, email, telefone);

        // Asserção
        assertFalse(cliente1.equals(cliente2));
    }
}
