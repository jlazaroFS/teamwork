package com.futurespace.teamwork.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.*;
import com.futurespace.teamwork.repositories.AssignmentRepository;

@Service
public class AssignmentService {
    private final AssignmentRepository repository;
    private final EmpleadoService empleadoService;
    private final ProyectoService proyectoService;

    public AssignmentService(AssignmentRepository repository, EmpleadoService empleadoService,
            ProyectoService proyectoService) {
        this.repository = repository;
        this.empleadoService = empleadoService;
        this.proyectoService = proyectoService;
    }

    public List<Assignment> getAllAssignment() {
        return repository.findAll();
    }

    public List<Long> getAssignedIdsToProject(Long projectId) {
        List<Assignment> assignments = repository.findById_IdProyecto(projectId);
        return assignments.stream()
                .map(assignment -> assignment.getId().getIdEmpleado())
                .collect(Collectors.toList());
    }

    public Assignment addAssignment(AssignmentId ids) {
        Empleado empleado = empleadoService.getEmpleadoById(ids.getIdEmpleado());
        Proyecto proyecto = proyectoService.getProyectoById(ids.getIdProyecto());

        if (empleado == null) {
            throw new IllegalArgumentException(
                    "No se ha encontrado ningún empleado con ID " + ids.getIdEmpleado());
        }

        if (proyecto == null) {
            throw new IllegalArgumentException(
                    "No se ha encontrado ningún proyecto con ID " + ids.getIdProyecto());
        }

        AssignmentId assignmentId = new AssignmentId();
        assignmentId.setIdProyecto(ids.getIdProyecto());
        assignmentId.setIdEmpleado(ids.getIdEmpleado());

        Assignment newAssignment = new Assignment();
        newAssignment.setId(assignmentId);
        newAssignment.setIdEmpleado(empleado);
        newAssignment.setIdProyecto(proyecto);
        newAssignment.setfAlta(Date.valueOf(LocalDate.now()));

        return repository.save(newAssignment);
    }

    public void deleteAssignment(AssignmentId ids) {
        Assignment assignmentToDelete = repository.findById(ids).orElse(null);

        if (assignmentToDelete == null) {
            throw new IllegalArgumentException(
                    "No se ha encontrado ninguna asignación con el ID de proyecto " + ids.getIdProyecto() +
                            " y el ID de empleado " + ids.getIdEmpleado());
        }

        repository.delete(assignmentToDelete);
    }

    public boolean hasAssignmentsForEmpleado(Long id) {
        return repository.existsById_IdEmpleado(id);
    }

    public boolean hasAssignmentsForProyecto(Long id) {
        return repository.existsById_IdProyecto(id);
    }
}
