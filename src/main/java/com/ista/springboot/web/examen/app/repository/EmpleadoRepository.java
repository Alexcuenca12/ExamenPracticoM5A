package com.ista.springboot.web.examen.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.springboot.web.examen.app.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{

}
