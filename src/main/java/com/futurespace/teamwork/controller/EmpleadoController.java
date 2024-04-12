package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.service.EmpleadoService;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:8081")
    @GetMapping
    public ResponseEntity<List<Empleado>> getAllEmpleado() {
        List<Empleado> empleados = service.getAllEmpleado();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado e) {
        Empleado newEmpleado = service.addEmpleado(e);
        return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
    }

    @PutMapping("baja/{id}")
    public ResponseEntity<Empleado> unlistEmpleado(@PathVariable Long id) {
        Empleado unlistedEmpleado = service.unlistEmpleado(id);
        return new ResponseEntity<>(unlistedEmpleado, HttpStatus.OK);
    }

}
