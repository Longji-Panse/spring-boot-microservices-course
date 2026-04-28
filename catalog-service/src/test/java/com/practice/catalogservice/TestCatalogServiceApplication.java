package com.practice.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestCatalogServiceApplication {

	@Bean
	@Primary
	@ServiceConnection
	PostgreSQLContainer<?> postgreSQLContainer() {
		return new PostgreSQLContainer<>(
				DockerImageName.parse("postgres:17"));
	}

	public static void main(String[] args) {
		SpringApplication
				.from(CatalogServiceApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

}
