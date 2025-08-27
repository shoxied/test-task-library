package org.example;

import org.example.entity.Client;
import org.example.repo.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@EntityScan(basePackageClasses = {Client.class})
@EnableJpaRepositories(basePackageClasses = {ClientRepository.class})
public class Application {
    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/test-task-library", "test-task-library", "test-task-library");
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE SCHEMA IF NOT EXISTS test_task_library");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create schema", e);
        }

        SpringApplication.run(Application.class, args);
    }
}