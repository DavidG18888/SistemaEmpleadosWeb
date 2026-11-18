package com.mycompany.empleados.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class LibSqlConfig {

    @Bean
    public DataSource dataSource() {

        String databaseUrl = System.getenv("TURSO_DATABASE_URL");
        String authToken = System.getenv("TURSO_AUTH_TOKEN");

        if (databaseUrl == null || databaseUrl.isBlank()) {
            throw new IllegalStateException(
                "Falta configurar TURSO_DATABASE_URL en Render."
            );
        }

        if (authToken == null || authToken.isBlank()) {
            throw new IllegalStateException(
                "Falta configurar TURSO_AUTH_TOKEN en Render."
            );
        }

        String jdbcUrl;

        if (databaseUrl.startsWith("libsql://")) {
            jdbcUrl = "jdbc:dbeaver:libsql:https://"
                    + databaseUrl.substring("libsql://".length());

        } else if (databaseUrl.startsWith("https://")) {
            jdbcUrl = "jdbc:dbeaver:libsql:" + databaseUrl;

        } else if (databaseUrl.startsWith("jdbc:dbeaver:libsql:")) {
            jdbcUrl = databaseUrl;

        } else {
            throw new IllegalArgumentException(
                "Formato de TURSO_DATABASE_URL no reconocido."
            );
        }

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(
            "com.dbeaver.jdbc.driver.libsql.LibSqlDriver"
        );

        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername("");
        dataSource.setPassword(authToken);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}