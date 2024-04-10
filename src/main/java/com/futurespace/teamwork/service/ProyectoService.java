package com.futurespace.teamwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.Proyecto;
import com.futurespace.teamwork.repositories.ProyectoRepository;

@Service
public class ProyectoService {
    private final ProyectoRepository repository;

    public ProyectoService(ProyectoRepository repository) {
        this.repository = repository;
    }

    public List<Proyecto> getAllProyecto() {
        return repository.findAll();
    }

    public Proyecto addProyecto(Proyecto p) {
        // TODO: Add validation
        return repository.save(p);
    }
}
