package com.ista.springboot.web.examen.app.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ista.springboot.web.examen.app.entity.Empleado;
import com.ista.springboot.web.examen.app.service.IEmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EmpleadosController {

	@Autowired
	private IEmpleadoService empleadoService;

	// List all
	@GetMapping("/empleado/list")
	public ResponseEntity<List<Empleado>> obtenerLista() {
		try {
			return new ResponseEntity<>(empleadoService.findByAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Save
	@PostMapping("/empleado/save")
	public ResponseEntity<?> createEmpl(@Valid @RequestBody Empleado emp) {
		try {

			if (emp.getDias_trabajo()>= 30) {
				emp.setSueldo((emp.getDias_trabajo() * 15.0) +(emp.getDias_trabajo() * 15) * 0.05);	
				return new ResponseEntity<>(empleadoService.save(emp), HttpStatus.CREATED);
			} else {
				if (emp.getDias_trabajo()>= 20) {
					emp.setSueldo((emp.getDias_trabajo() * 15.0) +(emp.getDias_trabajo() * 15) * 0.02);
					return new ResponseEntity<>(empleadoService.save(emp), HttpStatus.CREATED);
				}
			}
			emp.setSueldo((emp.getDias_trabajo() * 15.0));
			return new ResponseEntity<>(empleadoService.save(emp), HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/empleado/list/{id}")
	public ResponseEntity<?> readEmp(@PathVariable(value = "id") Long id) {
		Optional<Empleado> valid = Optional.ofNullable(empleadoService.findById(id));
		if (!valid.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(valid);
	}

	@PutMapping("/empleado/update/{id}")
	public ResponseEntity<?> updateEmpleado(@Valid @RequestBody Empleado emp, @PathVariable(value = "id") Long id) {
		Optional<Empleado> valid = Optional.ofNullable(empleadoService.findById(id));
		if (!valid.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		valid.get().setApellido(emp.getApellido());
		valid.get().setNombre(emp.getNombre());
		valid.get().setTelefono(emp.getTelefono());
		valid.get().setDireccion(emp.getDireccion());
		valid.get().setFecha_nacimiento(emp.getFecha_nacimiento());
		valid.get().setObservacion(emp.getObservacion());
		valid.get().setDias_trabajo(emp.getDias_trabajo());
		valid.get().setSueldo(emp.getSueldo());
		return ResponseEntity.status(HttpStatus.CREATED).body(empleadoService.save(valid.get()));

	}

	@DeleteMapping("/delete/empelado/{id}")
	public ResponseEntity<?> deleteEmpleado(@PathVariable(value = "id") Long id) {
		try {
			empleadoService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al elminar cancion");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
