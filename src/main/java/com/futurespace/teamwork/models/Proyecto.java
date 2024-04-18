package com.futurespace.teamwork.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "pr_proyectos")
public class Proyecto {
    @Id
    @SequenceGenerator(name = "sec_proyecto", sequenceName = "sec_proyecto", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_proyecto")
    @Column(name = "id_proyecto", updatable = false, nullable = false)
    private Long idProyecto;

    @Column(name = "TX_DESCRIPCIÓN", nullable = false)
    private String txDescripcion;

    @Column(name = "f_inicio", nullable = false)
    private Date fInicio;

    @Column(name = "f_fin", nullable = true)
    private Date fFin;

    @Column(name = "f_baja", nullable = true)
    private Date fBaja;

    @Column(name = "tx_lugar", nullable = true)
    private String txLugar;

    @Column(name = "tx_observaciones", nullable = true)
    private String txObservaciones;

    public Proyecto() {
    }

    public Proyecto(String txDescripcion, Date fInicio, Date fFin, String txLugar, String txObservaciones) {
        this.txDescripcion = txDescripcion;
        this.fInicio = fInicio;
        this.fFin = fFin;
        this.txLugar = txLugar;
        this.txObservaciones = txObservaciones;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getTxDescripcion() {
        return txDescripcion;
    }

    public void setTxDescripcion(String txDescripcion) {
        this.txDescripcion = txDescripcion;
    }

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfFin() {
        return fFin;
    }

    public void setfFin(Date fFin) {
        this.fFin = fFin;
    }

    public Date getfBaja() {
        return fBaja;
    }

    public void setfBaja(Date fBaja) {
        this.fBaja = fBaja;
    }

    public String getTxLugar() {
        return txLugar;
    }

    public void setTxLugar(String txLugar) {
        this.txLugar = txLugar;
    }

    public String getTxObservaciones() {
        return txObservaciones;
    }

    public void setTxObservaciones(String txObservaciones) {
        this.txObservaciones = txObservaciones;
    }
}
