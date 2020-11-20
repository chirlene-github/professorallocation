package com.project.professor.allocation.dto;

public class ProfessorBaseDTO {

	private Long id;
	private String name;
	private String cpf;
	private DepartmentBaseDTO department;

	public ProfessorBaseDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public DepartmentBaseDTO getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBaseDTO department) {
		this.department = department;
	}
}
