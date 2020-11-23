package com.project.professor.allocation.controller.mapper;

import com.project.professor.allocation.dto.DepartmentCompleteDTO;
import com.project.professor.allocation.dto.DepartmentCreationDTO;
import com.project.professor.allocation.dto.DepartmentSimpleDTO;
import com.project.professor.allocation.entity.Department;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    private final ModelMapper modelMapper;

    public DepartmentMapper() {
        this.modelMapper = new ModelMapper();
    }

    public List<DepartmentSimpleDTO> toDepartmentSimpleDTO(List<Department> departments) {
        return departments.stream().map(this::toDepartmentSimpleDTO).collect(Collectors.toList());
    }

    public DepartmentSimpleDTO toDepartmentSimpleDTO(Department department) {
        return modelMapper.map(department, DepartmentSimpleDTO.class);
    }

    public DepartmentCompleteDTO toDepartmentCompleteDTO(Department department) {
        return modelMapper.map(department, DepartmentCompleteDTO.class);
    }

    public Department toDepartment(DepartmentCreationDTO departmentCreationDTO) {
        return modelMapper.map(departmentCreationDTO, Department.class);
    }
}
