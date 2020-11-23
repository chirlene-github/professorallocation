package com.project.professor.allocation.controller;

import com.project.professor.allocation.controller.mapper.CourseMapper;
import com.project.professor.allocation.dto.CourseCompleteDTO;
import com.project.professor.allocation.dto.CourseCreationDTO;
import com.project.professor.allocation.dto.CourseSimpleDTO;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/course", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper mapper;

    public CourseController(CourseService courseService, CourseMapper mapper) {
        super();
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @ApiOperation(value = "Get all courses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CourseSimpleDTO>> getCourses(@RequestParam(name = "name", required = false) String name) {
        List<Course> courses = courseService.findAll(name);
        return new ResponseEntity<>(mapper.toCourseSimpleDTO(courses), HttpStatus.OK);
    }

    @ApiOperation(value = "Get course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseCompleteDTO> getCourse(@PathVariable(value = "id") Long id) {
        Course course = courseService.findById(id);
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toCourseCompleteDTO(course), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Create course")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseSimpleDTO> createCourse(@RequestBody CourseCreationDTO courseDTO) {
        Course course = courseService.save(mapper.toCourse(courseDTO));
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(mapper.toCourseSimpleDTO(course), HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Update course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CourseSimpleDTO> updateCourse(@PathVariable(value = "id") Long id,
                                                        @RequestBody CourseCreationDTO courseDTO) {
        courseDTO.setId(id);
        Course course = courseService.update(mapper.toCourse(courseDTO));
        if (course == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mapper.toCourseSimpleDTO(course), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Delete course")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCourse(@PathVariable(value = "id") Long id) {
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all courses")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCourses() {
        courseService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
