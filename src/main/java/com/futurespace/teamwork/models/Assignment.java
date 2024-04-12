package com.futurespace.teamwork.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pr_empleados_proyecto")
public class Assignment {
    @EmbeddedId
    private AssignmentId id;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", insertable = false, updatable = false)
    private Proyecto idProyecto;

    @ManyToOne
    @JoinColumn(name = "id_empleado", insertable = false, updatable = false)
    private Empleado idEmpleado;

    @Column(name = "f_alta", insertable = false, updatable = false)
    private Date fAlta;
}
