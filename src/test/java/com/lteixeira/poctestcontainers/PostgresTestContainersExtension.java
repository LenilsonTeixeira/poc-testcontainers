package com.lteixeira.poctestcontainers;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.concurrent.atomic.AtomicBoolean;

@Testcontainers
public class PostgresTestContainersExtension implements BeforeAllCallback {

    private static AtomicBoolean containerStarted = new AtomicBoolean(false);
    @Container
    private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:14.6-alpine"))
            .withDatabaseName("categories")
            .withUsername("admin")
            .withPassword("admin");
    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if(!containerStarted.get()) {
            postgresqlContainer.start();

            System.setProperty("spring.datasource.url", postgresqlContainer.getJdbcUrl());
            System.setProperty("spring.datasource.username", "admin");
            System.setProperty("spring.datasource.password", "admin");
            containerStarted.set(true);

        }
    }
}
