package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.*;

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

	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false, columnDefinition = "TIME")
	private Time startHour;

	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false, columnDefinition = "TIME")
	private Time endHour;

	@ManyToOne(optional = false)
	private Professor professor;

	@ManyToOne(optional = false)
	private Course course;
}
