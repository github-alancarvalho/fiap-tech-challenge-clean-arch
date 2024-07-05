package br.com.fiap.techchallenge.hexagonal.adapter.driven.infra.repositories;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolManager {

    private ConnectionPoolManager() {
        throw new IllegalStateException("Utility class");
    }

    private static HikariDataSource dataSource;

    public static synchronized HikariDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new HikariDataSource();
            dataSource.setJdbcUrl("jdbc:mariadb://" + System.getenv("MARIADB_DATABASE_ENDPOINT") + "/" + System.getenv("MARIADB_DATABASE"));
            dataSource.setUsername(System.getenv("MARIADB_USER"));
            dataSource.setPassword(System.getenv("MARIADB_PASSWORD"));
            dataSource.setMaximumPoolSize(10); // Tamanho máximo do pool
            dataSource.setConnectionTimeout(5000); // Tempo limite de espera por conexão
            dataSource.setValidationTimeout(30000); // Tempo limite de validação de conexão
            dataSource.setDriverClassName("org.mariadb.jdbc.Driver"); // Mariadb driver class
        }
        return dataSource;
    }

}