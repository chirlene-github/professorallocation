package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findAll(String name) {
		if (name == null) {
			return departmentRepository.findAll();
		} else {
			return departmentRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public Department findById(Long id) {
		return departmentRepository.findById(id).orElse(null);
	}

	public Department save(Department department) {
		department.setId(null);
		return internalSave(department);
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id == null) {
			return null;
		}

		Department findedDepartment = departmentRepository.findById(id).orElse(null);
		if (findedDepartment == null) {
			return null;
		}

		return internalSave(department);
	}

	public void deleteById(Long id) {
		try {
			departmentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}

	private Department internalSave(Department department) {
		try {
			return departmentRepository.save(department);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
