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
@Table(name = "em_empleados")
public class Empleado {
    @Id
    @SequenceGenerator(name = "sec_empleado", sequenceName = "sec_empleado", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_empleado")
    @Column(name = "id_empleado", updatable = false, nullable = false)
    private Long idEmpleado;

    @Column(name = "tx_nif", nullable = false)
    private String txNif;

    @Column(name = "tx_nombre", nullable = false)
    private String txNombre;

    @Column(name = "tx_apellido1", nullable = false)
    private String txApellido1;

    @Column(name = "tx_apellido2", nullable = false)
    private String txApellido2;

    @Column(name = "f_nacimiento", nullable = false)
    private Date fNacimiento;

    @Column(name = "n_telefono1", nullable = false)
    private Date nTelefono1;

    @Column(name = "n_telefono2", nullable = false)
    private Date nTelefono2;

    @Column(name = "tx_email", nullable = false)
    private String email;

    @Column(name = "f_alta", nullable = false)
    private Date fAlta;

    @Column(name = "f_baja", nullable = false)
    private Date fBaja;

    @Column(name = "cx_edocivil", nullable = false)
    private Character cxEdocivil;

    @Column(name = "b_servmilitar", nullable = false)
    private Character bServmilitar; // Not a boolean!

    public Empleado(String txNif, String txNombre, String txApellido1, String txApellido2, Date fNacimiento,
            Date nTelefono1, Date nTelefono2, String email, Date fAlta, Date fBaja, Character cxEdocivil,
            Character bServmilitar) {
        this.txNif = txNif;
        this.txNombre = txNombre;
        this.txApellido1 = txApellido1;
        this.txApellido2 = txApellido2;
        this.fNacimiento = fNacimiento;
        this.nTelefono1 = nTelefono1;
        this.nTelefono2 = nTelefono2;
        this.email = email;
        this.fAlta = fAlta;
        this.fBaja = fBaja;
        this.cxEdocivil = cxEdocivil;
        this.bServmilitar = bServmilitar;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getTxNif() {
        return txNif;
    }

    public void setTxNif(String txNif) {
        this.txNif = txNif;
    }

    public String getTxNombre() {
        return txNombre;
    }

    public void setTxNombre(String txNombre) {
        this.txNombre = txNombre;
    }

    public String getTxApellido1() {
        return txApellido1;
    }

    public void setTxApellido1(String txApellido1) {
        this.txApellido1 = txApellido1;
    }

    public String getTxApellido2() {
        return txApellido2;
    }

    public void setTxApellido2(String txApellido2) {
        this.txApellido2 = txApellido2;
    }

    public Date getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public Date getnTelefono1() {
        return nTelefono1;
    }

    public void setnTelefono1(Date nTelefono1) {
        this.nTelefono1 = nTelefono1;
    }

    public Date getnTelefono2() {
        return nTelefono2;
    }

    public void setnTelefono2(Date nTelefono2) {
        this.nTelefono2 = nTelefono2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getfAlta() {
        return fAlta;
    }

    public void setfAlta(Date fAlta) {
        this.fAlta = fAlta;
    }

    public Date getfBaja() {
        return fBaja;
    }

    public void setfBaja(Date fBaja) {
        this.fBaja = fBaja;
    }

    public Character getCxEdocivil() {
        return cxEdocivil;
    }

    public void setCxEdocivil(Character cxEdocivil) {
        this.cxEdocivil = cxEdocivil;
    }

    public Character getbServmilitar() {
        return bServmilitar;
    }

    public void setbServmilitar(Character bServmilitar) {
        this.bServmilitar = bServmilitar;
    }

}
