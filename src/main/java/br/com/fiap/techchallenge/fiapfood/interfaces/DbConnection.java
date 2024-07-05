package br.com.fiap.techchallenge.fiapfood.interfaces;

import jakarta.persistence.EntityManagerFactory;

public interface DbConnection {

    public EntityManagerFactory getConnection();
}
