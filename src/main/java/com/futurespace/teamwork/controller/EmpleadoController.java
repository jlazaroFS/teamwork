package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.service.EmpleadoService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        if (!isValidEdocivil(e.getCxEdocivil())) {
            throw new IllegalArgumentException("El estado civil debe ser soltero (S) o casado (C)");
        }
        if (!isValidServmilitar(e.getbServmilitar())) {
            throw new IllegalArgumentException("Se debe indicar si ha cumplido servicio militar (S) o no (N)");
        }
        return service.addEmpleado(e);
    }

    private boolean isValidEdocivil(Character cxEdocivil) {
        return (cxEdocivil == 'S' || cxEdocivil == 'C');
    }

    private boolean isValidServmilitar(Character bServmilitar) {
        return (bServmilitar == 'S' || bServmilitar == 'N');
    }

}
