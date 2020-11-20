package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "allocation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private DayOfWeek dayOfWeek;

	//@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Time startHour;

	//@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Time endHour;

	@ManyToOne(optional = false)
	private Professor professor;

	@ManyToOne(optional = false)
	private Course course;
}
