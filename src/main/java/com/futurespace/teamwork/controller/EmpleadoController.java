package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.service.EmpleadoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("empleados")
public class EmpleadoController {
    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Empleado> getAllEmpleado() {
        return service.getAllEmpleado();
    }

    @PostMapping
    public Empleado addEmpleado(@RequestBody Empleado e) {
        return service.addEmpleado(e);
    }

    @PutMapping("baja/{id}")
    public Empleado unlistEmpleado(@PathVariable Long id) {
        return service.unlistEmpleado(id);
    }

}
