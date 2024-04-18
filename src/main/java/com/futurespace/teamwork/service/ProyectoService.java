package com.futurespace.teamwork.service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.futurespace.teamwork.models.Proyecto;
import com.futurespace.teamwork.repositories.ProyectoRepository;
import com.futurespace.teamwork.repositories.AssignmentRepository;

@Service
public class ProyectoService {
    private final ProyectoRepository repository;
    private final AssignmentRepository assignmentRepository;

    public ProyectoService(ProyectoRepository repository, AssignmentRepository assignmentRepository) {
        this.repository = repository;
        this.assignmentRepository = assignmentRepository;
    }

    public List<Proyecto> getAllProyecto() {
        return repository.findAll();
    }

    public Proyecto addProyecto(Proyecto p) {
        // Validation checks
        if (!areValidDates(p)) {
            throw new IllegalArgumentException(
                    "Un proyecto no puede finalizar ni ser dado de baja antes de que haya comenzado");
        }
        return repository.save(p);
    }

    private boolean areValidDates(Proyecto p) {
        Date start = p.getfInicio();
        Date end = p.getfFin();
        Date unlist = p.getfBaja();

        return !(end != null && !start.before(end)) || (unlist != null && !start.before(unlist));
    }

    private Proyecto modifyFbajaProyecto(Long id, Date newFbaja) {
        Proyecto p = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("No se ha encontrado ningún proyecto con ID: " + id));

        if (newFbaja.before(p.getfInicio())) {
            throw new IllegalArgumentException("La fecha de baja debe ser posterior a la fecha de inicio");
        }

        Date today = Date.valueOf(LocalDate.now());

        if (newFbaja.after(today)) {
            throw new IllegalArgumentException("La fecha de baja no puede ser posterior a la fecha de hoy");
        }

        p.setfBaja(newFbaja);
        return repository.save(p);
    }

    public Proyecto getProyectoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Proyecto unlistProyecto(Long id) {
        Proyecto proyecto = repository.findById(id).orElse(null);
        if (proyecto == null) {
            throw new IllegalArgumentException("No se ha encontrado ningún proyecto con ID: " + id);
        }

        // Check if the project has any assignments
        boolean hasAssignments = assignmentRepository.existsById_IdProyecto(id);
        if (hasAssignments) {
            throw new IllegalStateException(
                    "No se puede dar de baja el proyecto " + proyecto.getTxDescripcion()
                            + " porque tiene empleados asignados.");
        }

        Date today = Date.valueOf(LocalDate.now());
        return modifyFbajaProyecto(id, today);
    }
}
