package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AllocationId implements Serializable {

    //@Column(name = "professor_id")
    private long professorId;

    //@Column(name = "course_id")
    private long courseId;
}
