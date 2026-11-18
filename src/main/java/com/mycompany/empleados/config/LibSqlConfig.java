package com.mycompany.empleados.config;
import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDataSource;
@Configuration
public class LibSqlConfig {
  @Bean
  public JdbcTemplate jdbcTemplate() {
    String url = System.getenv("TURSO_DATABASE_URL");
    String token = System.getenv("TURSO_AUTH_TOKEN");
    if (url == null || url.isBlank()) url = "jdbc:dbeaver:libsql:http://localhost:8080";
    else {
      if (url.startsWith("libsql://")) url = "jdbc:dbeaver:libsql:https://" + url.substring("libsql://".length());
      else if (url.startsWith("https://")) url = "jdbc:dbeaver:libsql:" + url;
      else if (!url.startsWith("jdbc:dbeaver:libsql:")) url = "jdbc:dbeaver:libsql:" + url;
    }
    final String jdbcUrl=url, auth=token;
    AbstractDataSource ds = new AbstractDataSource() {
      @Override public Connection getConnection() throws java.sql.SQLException { return DriverManager.getConnection(jdbcUrl, null, auth); }
      @Override public Connection getConnection(String username, String password) throws java.sql.SQLException { return DriverManager.getConnection(jdbcUrl, username, password); }
    };
    return new JdbcTemplate(ds);
  }
}
