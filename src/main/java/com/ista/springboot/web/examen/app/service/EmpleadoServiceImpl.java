package com.ista.springboot.web.examen.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.ista.springboot.web.examen.app.entity.Empleado;
import com.ista.springboot.web.examen.app.repository.EmpleadoRepository;

@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, Long> implements IEmpleadoService {
	@Autowired
	EmpleadoRepository empleadoRepository;

	@Override
	public CrudRepository<Empleado, Long> getDao() {
		// TODO Auto-generated method stub
		return empleadoRepository;
	}

}
