package com.project.professor.allocation.controller.mapper;

import com.project.professor.allocation.dto.CourseCompleteDTO;
import com.project.professor.allocation.dto.CourseCreationDTO;
import com.project.professor.allocation.dto.CourseSimpleDTO;
import com.project.professor.allocation.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private final ModelMapper modelMapper;

    public CourseMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<CourseSimpleDTO> toCourseSimpleDTO(List<Course> courses) {
        return courses.stream().map(this::toCourseSimpleDTO).collect(Collectors.toList());
    }

    public CourseSimpleDTO toCourseSimpleDTO(Course course) {
        return modelMapper.map(course, CourseSimpleDTO.class);
    }

    public CourseCompleteDTO toCourseCompleteDTO(Course course) {
        return modelMapper.map(course, CourseCompleteDTO.class);
    }

    public Course toCourse(CourseCreationDTO courseCreationDTO) {
        return modelMapper.map(courseCreationDTO, Course.class);
    }
}
