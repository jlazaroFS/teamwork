package com.futurespace.teamwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.futurespace.teamwork.models.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

}