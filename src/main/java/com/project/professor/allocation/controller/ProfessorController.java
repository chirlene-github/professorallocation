package com.project.professor.allocation.controller;

import com.project.professor.allocation.controller.mapper.ProfessorMapper;
import com.project.professor.allocation.dto.ProfessorCompleteDTO;
import com.project.professor.allocation.dto.ProfessorCreationDTO;
import com.project.professor.allocation.dto.ProfessorSimpleDTO;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professor", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController {

    private final ProfessorService professorService;
    private final ProfessorMapper mapper;

    public ProfessorController(ProfessorService professorService, ProfessorMapper mapper) {
        super();
        this.professorService = professorService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Get all professors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProfessorSimpleDTO>> getProfessors(@RequestParam(name = "name", required = false) String name) {
        List<Professor> professors = professorService.findAll(name);
        return new ResponseEntity<>(mapper.toProfessorSimpleDTO(professors), HttpStatus.OK);
    }

    @ApiOperation(value = "Get professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfessorCompleteDTO> getProfessor(@PathVariable(value = "id") Long id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toProfessorCompleteDTO(professor), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Create professor")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProfessorSimpleDTO> createProfessor(@RequestBody ProfessorCreationDTO professorDTO) {
        Professor professor = professorService.save(mapper.toProfessor(professorDTO));
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mapper.toProfessorSimpleDTO(professor), HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Update professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProfessorSimpleDTO> updateProfessor(@PathVariable(value = "id") Long id,
                                                              @RequestBody ProfessorCreationDTO professorDTO) {
        professorDTO.setId(id);
        Professor professor = professorService.update(mapper.toProfessor(professorDTO));
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toProfessorSimpleDTO(professor), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete professor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProfessor(@PathVariable(value = "id") Long id) {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all professors")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProfessors() {
        professorService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
