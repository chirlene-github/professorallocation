package com.project.professor.allocation.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AllocationSimpleDTO extends AllocationBaseDTO {

    private ProfessorBaseDTO professor;
    private CourseBaseDTO course;
}
