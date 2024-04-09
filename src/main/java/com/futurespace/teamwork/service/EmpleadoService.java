package com.futurespace.teamwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.Empleado;
import com.futurespace.teamwork.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public List<Empleado> getAllEmpleado() {
        return repository.findAll();
    }

    public Empleado addEmpleado(Empleado e) {
        return repository.save(e);
    }
}
