package com.mycompany.empleados.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
@Component
public class DatabaseInitializer implements CommandLineRunner {
 private final JdbcTemplate jdbc;
 public DatabaseInitializer(JdbcTemplate jdbc){this.jdbc=jdbc;}
 @Override public void run(String... args){
   jdbc.execute("CREATE TABLE IF NOT EXISTS Empleados (IdEmpleado INTEGER PRIMARY KEY AUTOINCREMENT, Nombre TEXT NOT NULL, Apellido TEXT NOT NULL, Cargo TEXT, Salario REAL NOT NULL DEFAULT 0, Departamento TEXT, Estado TEXT NOT NULL DEFAULT 'ACTIVO')");
 }
}
