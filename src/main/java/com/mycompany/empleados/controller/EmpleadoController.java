package com.mycompany.empleados.controller;
import com.mycompany.empleados.dao.EmpleadoDAO;
import com.mycompany.empleados.model.Empleado;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class EmpleadoController {
 private final EmpleadoDAO dao;
 public EmpleadoController(EmpleadoDAO dao){this.dao=dao;}
 @GetMapping("/") public String inicio(Model m){m.addAttribute("empleados",dao.listar());m.addAttribute("total",dao.listar().size());return "index";}
 @GetMapping("/empleados") public String empleados(@RequestParam(required=false) String q,Model m){m.addAttribute("empleados",q==null||q.isBlank()?dao.listar():dao.buscar(q));m.addAttribute("q",q);m.addAttribute("total",dao.listar().size());return "index";}
 @GetMapping("/nuevo") public String nuevo(Model m){m.addAttribute("empleado",new Empleado());m.addAttribute("titulo","Registrar empleado");return "formulario";}
 @PostMapping("/guardar") public String guardar(@ModelAttribute Empleado e){dao.guardar(e);return "redirect:/?guardado";}
 @GetMapping("/editar/{id}") public String editar(@PathVariable int id,Model m){m.addAttribute("empleado",dao.obtener(id));m.addAttribute("titulo","Editar empleado");return "formulario";}
 @PostMapping("/actualizar") public String actualizar(@ModelAttribute Empleado e){dao.actualizar(e);return "redirect:/?actualizado";}
 @PostMapping("/eliminar/{id}") public String eliminar(@PathVariable int id){dao.eliminar(id);return "redirect:/?eliminado";}
}
