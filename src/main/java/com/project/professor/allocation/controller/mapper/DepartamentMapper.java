package com.project.professor.allocation.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.project.professor.allocation.dto.DepartmentBaseDTO;
import com.project.professor.allocation.dto.DepartmentDTO;
import com.project.professor.allocation.model.Department;

@Component
public class DepartmentMapper {

	private ModelMapper modelMapper;

	public DepartmentMapper() {
		this.modelMapper = new ModelMapper();
	}

	public List<DepartmentBaseDTO> toDepartmentBaseDTO(List<Department> departments) {
		return departments.stream().map(this::toDepartmentBaseDTO).collect(Collectors.toList());
	}

	public DepartmentBaseDTO toDepartmentBaseDTO(Department department) {
		return modelMapper.map(department, DepartmentBaseDTO.class);
	}

	public DepartmentDTO toDepartmentDTO(Department department) {
		return modelMapper.map(department, DepartmentDTO.class);
	}

	public Department toDepartment(DepartmentBaseDTO departmentBaseDTO) {
		return modelMapper.map(departmentBaseDTO, Department.class);
	}
}
