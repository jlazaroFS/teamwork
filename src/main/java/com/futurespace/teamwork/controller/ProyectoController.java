package com.futurespace.teamwork.controller;

import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Proyecto;
import com.futurespace.teamwork.service.ProyectoService;

import java.util.List;

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
    public List<Proyecto> getAllProyecto() {
        return service.getAllProyecto();
    }

    @PostMapping
    public Proyecto addProyecto(@RequestBody Proyecto p) {
        return service.addProyecto(p);
    }

    @PutMapping("baja/{idProyecto}")
    public Proyecto unlistProyecto(@PathVariable Long idProyecto) {
        return service.unlistProyecto(idProyecto);
    }
}
