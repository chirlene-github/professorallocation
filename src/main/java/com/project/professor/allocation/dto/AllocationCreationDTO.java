package com.project.professor.allocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AllocationCreationDTO extends AllocationBaseDTO {

    private Long professorId;
    private Long courseId;
}
