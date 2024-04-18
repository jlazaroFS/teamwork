package com.futurespace.teamwork.service;

import java.time.LocalDate;
import java.sql.Date;
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

    public Proyecto unlistProyecto(Long id) {
        Date today = Date.valueOf(LocalDate.now());
        return modifyFbajaProyecto(id, today);
    }

    public Proyecto getProyectoById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
