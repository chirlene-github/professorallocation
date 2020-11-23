package com.project.professor.allocation.repository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import com.project.professor.allocation.entity.AllocationId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	@Autowired
	private AllocationRepository allocationRepository;

	@Test
	public void findById() {
		// Arrange
		Long id = 1L;

		// Act
		Allocation allocation = null;// = allocationRepository.findById(id).orElse(null);

		// Print
		System.out.println("\n\n\n");
		System.out.println(allocationRepository.findAll());
		System.out.println("\n\n\n");
		System.out.println(allocationRepository.findById(new AllocationId(1L, 1L)).orElse(null));
		System.out.println("\n\n\n");
	}

}
