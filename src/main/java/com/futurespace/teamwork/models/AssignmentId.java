package com.futurespace.teamwork.models;

import java.io.Serializable;

import jakarta.persistence.Column;

public class AssignmentId implements Serializable {
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "id_empleado")
    private Long idEmpleado;

    public AssignmentId() {
    }

    public AssignmentId(Long idProyecto, Long idEmpleado) {
        this.idProyecto = idProyecto;
        this.idEmpleado = idEmpleado;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}
