package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.controller.mapper.DepartmentMapper;
import com.project.professor.allocation.dto.DepartmentBaseDTO;
import com.project.professor.allocation.dto.DepartmentDTO;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

	private DepartmentService departmentService;
	private DepartmentMapper mapper;

	public DepartmentController(DepartmentService departmentService, DepartmentMapper mapper) {
		super();
		this.departmentService = departmentService;
		this.mapper = mapper;
	}

	@ApiOperation(value = "Get all departments")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<DepartmentBaseDTO>> getDepartments(
			@RequestParam(name = "name", required = false) String name) {
		List<Department> departments = departmentService.findAll(name);
		return new ResponseEntity<>(mapper.toDepartmentBaseDTO(departments), HttpStatus.OK);
	}

	@ApiOperation(value = "Get department")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable(value = "id") Long id) {
		Department department = departmentService.findById(id);
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toDepartmentDTO(department), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Create department")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Created"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DepartmentBaseDTO> createDepartment(@RequestBody DepartmentBaseDTO departmentBaseDTO) {
		Department department = departmentService.save(mapper.toDepartment(departmentBaseDTO));
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toDepartmentBaseDTO(department), HttpStatus.CREATED);
		}
	}

	@ApiOperation(value = "Update department")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<DepartmentBaseDTO> updateDepartment(@PathVariable(value = "id") Long id,
			@RequestBody DepartmentBaseDTO departmentBaseDTO) {
		departmentBaseDTO.setId(id);
		Department department = departmentService.update(mapper.toDepartment(departmentBaseDTO));
		if (department == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toDepartmentBaseDTO(department), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Delete department")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 400, message = "Bad Request")
	})
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartment(@PathVariable(value = "id") Long id) {
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all departments")
	@ApiResponses({
		@ApiResponse(code = 204, message = "No Content")
	})
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteDepartments() {
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
