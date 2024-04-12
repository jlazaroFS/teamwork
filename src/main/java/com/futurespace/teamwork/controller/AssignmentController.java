package com.futurespace.teamwork.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futurespace.teamwork.models.Assignment;
import com.futurespace.teamwork.service.AssignmentService;

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
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }
}
