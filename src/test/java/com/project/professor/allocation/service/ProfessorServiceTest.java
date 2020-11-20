package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Test
	public void findAll() {
		// Arrange
		String name = null;

		// Act
		List<Professor> professors = professorService.findAll(name);

		// Print
		professors.forEach(System.out::println);
	}

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Professor professor = professorService.findById(id);

		// Print
		System.out.println(professor);
	}

	@Test
	public void save() {
		// Arrange
		Department department = new Department();
		department.setId(1L);

		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Professor 1");
		professor.setCpf("111.111.111-11");
		professor.setDepartment(department);

		// Act
		professor = professorService.save(professor);

		// Print
		System.out.println(professor);
	}

	@Test
	public void update() {
		// Arrange
		Department department = new Department();
		department.setId(1L);

		Professor professor = new Professor();
		professor.setId(1L);
		professor.setName("Professor 2");
		professor.setCpf("222.222.222-22");
		professor.setDepartment(department);

		// Act
		professor = professorService.update(professor);

		// Print
		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		// Arrange
		Long id = 1L;

		// Act
		professorService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		// Act
		professorService.deleteAll();
	}
}
