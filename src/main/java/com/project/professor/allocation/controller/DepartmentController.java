package com.project.professor.allocation.controller;

import com.project.professor.allocation.controller.mapper.DepartmentMapper;
import com.project.professor.allocation.dto.DepartmentCompleteDTO;
import com.project.professor.allocation.dto.DepartmentCreationDTO;
import com.project.professor.allocation.dto.DepartmentSimpleDTO;
import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/department", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper mapper;

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
    public ResponseEntity<List<DepartmentSimpleDTO>> getDepartments(@RequestParam(name = "name", required = false) String name) {
        List<Department> departments = departmentService.findAll(name);
        return new ResponseEntity<>(mapper.toDepartmentSimpleDTO(departments), HttpStatus.OK);
    }

    @ApiOperation(value = "Get department")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DepartmentCompleteDTO> getDepartment(@PathVariable(value = "id") Long id) {
        Department department = departmentService.findById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toDepartmentCompleteDTO(department), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Create department")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DepartmentSimpleDTO> createDepartment(@RequestBody DepartmentCreationDTO departmentDTO) {
        Department department = departmentService.save(mapper.toDepartment(departmentDTO));
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mapper.toDepartmentSimpleDTO(department), HttpStatus.CREATED);
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
    public ResponseEntity<DepartmentSimpleDTO> updateDepartment(@PathVariable(value = "id") Long id,
                                                                @RequestBody DepartmentCreationDTO departmentDTO) {
        departmentDTO.setId(id);
        Department department = departmentService.update(mapper.toDepartment(departmentDTO));
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toDepartmentSimpleDTO(department), HttpStatus.OK);
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
