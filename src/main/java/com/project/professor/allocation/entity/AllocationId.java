package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllocationId implements Serializable {

    private Professor professor;

    private Course course;

    public AllocationId(Long professorId, Long courseId) {
        this.professor = new Professor();
        this.course = new Course();

        this.professor.setId(professorId);
        this.course.setId(courseId);
    }
}
