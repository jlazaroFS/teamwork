package com.futurespace.teamwork.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Assignment;
import com.futurespace.teamwork.models.AssignmentId;
import com.futurespace.teamwork.service.AssignmentService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("assignments")
public class AssignmentController {
    private final AssignmentService service;

    public AssignmentController(AssignmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignment() {
        List<Assignment> assignments = service.getAllAssignment();
        return new ResponseEntity<>(assignments, assignments.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Assignment> addAssignment(@RequestBody AssignmentId ids) {
        Assignment newAssignment = service.addAssignment(ids);
        return new ResponseEntity<>(newAssignment, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{idProyecto}/{idEmpleado}")
    public ResponseEntity<String> deleteAssignment(@PathVariable Long idProyecto, @PathVariable Long idEmpleado) {
        AssignmentId ids = new AssignmentId();
        ids.setIdProyecto(idProyecto);
        ids.setIdEmpleado(idEmpleado);
        service.deleteAssignment(ids);

        return new ResponseEntity<>("Se ha eliminado la asignación correctamente.", HttpStatus.OK);
    }
}
