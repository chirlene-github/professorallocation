package com.project.professor.allocation.service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.AllocationId;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    private final AllocationRepository allocationRepository;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
                             CourseService courseService) {
        super();
        this.allocationRepository = allocationRepository;
        this.professorService = professorService;
        this.courseService = courseService;
    }

    public List<Allocation> findAll() {
        return allocationRepository.findAll();
    }

    public List<Allocation> findByProfessor(Long id) {
        Professor professor = new Professor();
        professor.setId(id);
        return allocationRepository.findByProfessor(professor);
    }

    public List<Allocation> findByCourse(Long id) {
        Course course = new Course();
        course.setId(id);
        return allocationRepository.findByCourse(course);
    }

    public Allocation findById(AllocationId id) {
        return allocationRepository.findById(id).orElse(null);
    }

    public Allocation save(Allocation allocation) {
        return internalSave(allocation);
    }

    public Allocation update(Allocation allocation) {
        Professor professor = allocation.getProfessor();
        Course course = allocation.getCourse();
        if (professor == null || professor.getId() == null || course == null || course.getId() == null) {
            return null;
        }

        AllocationId id = new AllocationId(professor, course);
        if (allocationRepository.existsById(id)) {
            return null;
        }

        return internalSave(allocation);
    }

    public void deleteById(AllocationId id) {
        try {
            allocationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        allocationRepository.deleteAllInBatch();
    }

    private Allocation internalSave(Allocation allocation) {
        try {
            if (!hasCollision(allocation)) {
                allocation = allocationRepository.save(allocation);

                Professor professor = allocation.getProfessor();
                professor = professorService.findById(professor.getId());
                allocation.setProfessor(professor);

                Course course = allocation.getCourse();
                course = courseService.findById(course.getId());
                allocation.setCourse(course);

                return allocation;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean hasCollision(Allocation newAllocation) {
        boolean hasCollision = false;

        List<Allocation> currentAllocations = allocationRepository.findByProfessor(newAllocation.getProfessor());

        for (Allocation currentAllocation : currentAllocations) {
            hasCollision = hasCollision(currentAllocation, newAllocation);
            if (hasCollision) {
                break;
            }
        }

        return hasCollision;
    }

    private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
        return currentAllocation.getDayOfWeek() == newAllocation.getDayOfWeek()
                && currentAllocation.getStartHour().compareTo(newAllocation.getEndHour()) < 0
                && newAllocation.getStartHour().compareTo(currentAllocation.getEndHour()) < 0;
    }
}
