package com.project.professor.allocation.dto;

import java.util.List;

public class DepartmentDTO extends DepartmentBaseDTO {

	private List<ProfessorBaseDTO> professors;

	public DepartmentDTO() {
		super();
	}

	public List<ProfessorBaseDTO> getProfessors() {
		return professors;
	}

	public void setProfessors(List<ProfessorBaseDTO> professors) {
		this.professors = professors;
	}
}
