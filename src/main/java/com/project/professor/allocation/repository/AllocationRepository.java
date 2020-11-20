package com.project.professor.allocation.repository;

import java.util.List;

import com.project.professor.allocation.entity.AllocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, AllocationId> {

	List<Allocation> findByProfessor(Professor professor);

	List<Allocation> findByCourse(Course course);
}
