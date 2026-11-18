package com.mycompany.empleados.dao;

import com.mycompany.empleados.model.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO {

    private final JdbcTemplate jdbc;

    public EmpleadoDAO(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<Empleado> mapper = (rs, n) -> {
        Empleado e = new Empleado();

        e.setIdEmpleado(rs.getInt("IdEmpleado"));
        e.setNombre(rs.getString("Nombre"));
        e.setApellido(rs.getString("Apellido"));
        e.setCargo(rs.getString("Cargo"));
        e.setSalario(rs.getDouble("Salario"));
        e.setDepartamento(rs.getString("Departamento"));
        e.setEstado(rs.getString("Estado"));

        return e;
    };

    public List<Empleado> listar() {
        return jdbc.query(
                "SELECT * FROM Empleados ORDER BY IdEmpleado DESC",
                mapper
        );
    }

    public List<Empleado> buscar(String q) {
        String s = "%" + (q == null ? "" : q.trim()) + "%";

        return jdbc.query(
                "SELECT * FROM Empleados "
                + "WHERE Nombre LIKE ? OR Apellido LIKE ? "
                + "OR Cargo LIKE ? OR Departamento LIKE ? "
                + "ORDER BY IdEmpleado DESC",
                mapper,
                s, s, s, s
        );
    }

    public Empleado obtener(int id) {
        return jdbc.query(
                "SELECT * FROM Empleados WHERE IdEmpleado=?",
                mapper,
                id
        ).stream().findFirst().orElse(null);
    }

    // Método alternativo para ejecutar operaciones de escritura
    private int ejecutarUpdate(String sql, Object... parametros) {

        return jdbc.execute((ConnectionCallback<Integer>) (Connection con) -> {

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                for (int i = 0; i < parametros.length; i++) {
                    ps.setObject(i + 1, parametros[i]);
                }

                ps.execute();

                return ps.getUpdateCount();
            }
        });
    }

    public int guardar(Empleado e) {

        String sql = "INSERT INTO Empleados "
                + "(Nombre,Apellido,Cargo,Salario,Departamento,Estado) "
                + "VALUES(?,?,?,?,?,?)";

        return ejecutarUpdate(
                sql,
                e.getNombre(),
                e.getApellido(),
                e.getCargo(),
                e.getSalario(),
                e.getDepartamento(),
                e.getEstado() == null ? "ACTIVO" : e.getEstado()
        );
    }

    public int actualizar(Empleado e) {

        String sql = "UPDATE Empleados SET "
                + "Nombre=?,Apellido=?,Cargo=?,Salario=?,"
                + "Departamento=?,Estado=? "
                + "WHERE IdEmpleado=?";

        return ejecutarUpdate(
                sql,
                e.getNombre(),
                e.getApellido(),
                e.getCargo(),
                e.getSalario(),
                e.getDepartamento(),
                e.getEstado(),
                e.getIdEmpleado()
        );
    }

    public int eliminar(int id) {

        return ejecutarUpdate(
                "DELETE FROM Empleados WHERE IdEmpleado=?",
                id
        );
    }
}