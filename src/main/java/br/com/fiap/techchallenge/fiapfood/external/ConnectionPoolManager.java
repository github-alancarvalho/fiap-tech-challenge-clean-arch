package br.com.fiap.techchallenge.fiapfood.external;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component("dbConnection")
public class ConnectionPoolManager {

    private EntityManagerFactory entityManagerFactory;

    public ConnectionPoolManager(){
        this.entityManagerFactory = getEntityManagerProperties();
    }


    public EntityManagerFactory getEntityManagerProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put("jakarta.persistence.jdbc.driver", "org.mariadb.jdbc.Driver");
        properties.put("jakarta.persistence.jdbc.url", "jdbc:mariadb://" + System.getenv("MARIADB_DATABASE_ENDPOINT") + "/" + System.getenv("MARIADB_DATABASE"));
        properties.put("jakarta.persistence.jdbc.user", System.getenv("MARIADB_USER"));
        properties.put("jakarta.persistence.jdbc.password", System.getenv("MARIADB_PASSWORD"));
        properties.put("hibernate.cache.use_second_level_cache", "false");

        return Persistence.createEntityManagerFactory("fiap-tech-challenge", properties);
    }

    public EntityManagerFactory getConnection() {
        return entityManagerFactory;
    }
}