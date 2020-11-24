package com.project.professor.allocation.controller;

import com.project.professor.allocation.controller.mapper.AllocationMapper;
import com.project.professor.allocation.dto.AllocationCompleteDTO;
import com.project.professor.allocation.dto.AllocationCreationDTO;
import com.project.professor.allocation.dto.AllocationSimpleDTO;
import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.AllocationId;
import com.project.professor.allocation.service.AllocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/allocation", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController {

    private final AllocationService allocationService;
    private final AllocationMapper mapper;

    public AllocationController(AllocationService allocationService, AllocationMapper mapper) {
        super();
        this.allocationService = allocationService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Get all allocations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> getAllocations() {
        List<Allocation> allocations = allocationService.findAll();
        return new ResponseEntity<>(mapper.toAllocationSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Get allocation by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/professor/{professor_id}/course/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AllocationCompleteDTO> getAllocation(@PathVariable(value = "professor_id") Long professorId,
                                                               @PathVariable(value = "professor_id") Long courseId) {
        Allocation allocation = allocationService.findById(new AllocationId(professorId, courseId));
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toAllocationCompleteDTO(allocation), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get all allocations by professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/professor/{professor_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> getAllocationsByProfessor(@PathVariable(value = "professor_id") Long id) {
        List<Allocation> allocations = allocationService.findByProfessor(id);
        return new ResponseEntity<>(mapper.toAllocationSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all allocations by course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/course/{course_id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AllocationSimpleDTO>> getAllocationsByCourse(@PathVariable(value = "course_id") Long id) {
        List<Allocation> allocations = allocationService.findByCourse(id);
        return new ResponseEntity<>(mapper.toAllocationSimpleDTO(allocations), HttpStatus.OK);
    }

    @ApiOperation(value = "Create allocation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AllocationSimpleDTO> createAllocation(@RequestBody AllocationCreationDTO allocationDTO) {
        Allocation allocation = allocationService.save(mapper.toAllocation(allocationDTO));
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mapper.toAllocationSimpleDTO(allocation), HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Update allocation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(value = "/professor/{professor_id}/course/{course_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AllocationSimpleDTO> updateAllocation(@PathVariable(value = "professor_id") Long professorId,
                                                                @PathVariable(value = "professor_id") Long courseId,
                                                                @RequestBody AllocationCreationDTO allocationDTO) {
        allocationDTO.setProfessorId(professorId);
        allocationDTO.setCourseId(courseId);
        Allocation allocation = allocationService.update(mapper.toAllocation(allocationDTO));
        if (allocation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toAllocationSimpleDTO(allocation), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete allocation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping(value = "/professor/{professor_id}/course/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllocation(@PathVariable(value = "professor_id") Long professorId,
                                                 @PathVariable(value = "course_id") Long courseId) {
        allocationService.deleteById(new AllocationId(professorId, courseId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all allocations")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAllocations() {
        allocationService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
