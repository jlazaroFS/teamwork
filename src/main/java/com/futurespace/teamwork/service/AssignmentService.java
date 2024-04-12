package com.futurespace.teamwork.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.Assignment;
import com.futurespace.teamwork.repositories.AssignmentRepository;

@Service
public class AssignmentService {
    private final AssignmentRepository repository;

    public AssignmentService(AssignmentRepository repository) {
        this.repository = repository;
    }

    public List<Assignment> getAllAssignment() {
        return repository.findAll();
    }

    public Assignment addAssignment(Assignment a) {
        // TODO: Check employee and project both exist?

        return repository.save(a);
    }
}
