package com.futurespace.teamwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futurespace.teamwork.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    boolean existsByTxNif(String txNif);

    boolean existsByEmail(String email);

}
