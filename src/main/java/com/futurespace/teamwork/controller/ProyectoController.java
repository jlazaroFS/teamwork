package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Proyecto;
import com.futurespace.teamwork.service.ProyectoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("proyectos")
public class ProyectoController {
    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Proyecto>> getAllProyecto() {
        List<Proyecto> proyectos = service.getAllProyecto();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proyecto> addProyecto(@RequestBody Proyecto p) {
        Proyecto newProyecto = service.addProyecto(p);
        return new ResponseEntity<>(newProyecto, HttpStatus.CREATED);
    }

    @PutMapping("baja/{idProyecto}")
    public ResponseEntity<Proyecto> unlistProyecto(@PathVariable Long idProyecto) {
        Proyecto unlistedProyecto = service.unlistProyecto(idProyecto);
        return new ResponseEntity<>(unlistedProyecto, HttpStatus.OK);
    }
}
