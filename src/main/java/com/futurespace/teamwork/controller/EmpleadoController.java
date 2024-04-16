package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.service.EmpleadoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:8081")
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

    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado e) {
        Empleado newEmpleado = service.addEmpleado(e);
        return new ResponseEntity<>(newEmpleado, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:8081")
    @PutMapping("baja/{id}")
    public ResponseEntity<Empleado> unlistEmpleado(@PathVariable Long id) {
        Empleado unlistedEmpleado = service.unlistEmpleado(id);
        return new ResponseEntity<>(unlistedEmpleado, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8081", methods = { RequestMethod.OPTIONS })
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        return ResponseEntity.ok()
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE")
                .header("Access-Control-Allow-Headers", "Authorization, Content-Type")
                .header("Access-Control-Max-Age", "3600")
                .build();
    }

}
