package com.futurespace.teamwork.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futurespace.teamwork.models.Assignment;
import com.futurespace.teamwork.models.AssignmentId;

public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentId> {

    boolean existsById_IdEmpleado(Long id);

    boolean existsById_IdProyecto(Long id);

    List<Assignment> findById_IdProyecto(Long idProyecto);

}
