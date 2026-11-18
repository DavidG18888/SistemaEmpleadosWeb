package com.mycompany.empleados.model;
public class Empleado {
 private Integer idEmpleado; private String nombre, apellido, cargo, departamento, estado; private double salario;
 public Integer getIdEmpleado(){return idEmpleado;} public void setIdEmpleado(Integer v){idEmpleado=v;}
 public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getApellido(){return apellido;} public void setApellido(String v){apellido=v;}
 public String getCargo(){return cargo;} public void setCargo(String v){cargo=v;}
 public String getDepartamento(){return departamento;} public void setDepartamento(String v){departamento=v;}
 public String getEstado(){return estado;} public void setEstado(String v){estado=v;}
 public double getSalario(){return salario;} public void setSalario(double v){salario=v;}
}
