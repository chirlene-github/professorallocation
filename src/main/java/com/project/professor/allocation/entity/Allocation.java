package com.project.professor.allocation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.*;

@Entity
@Table(name = "allocation")
@IdClass(AllocationId.class)

@AttributeOverrides({
		@AttributeOverride(name = "professorId", column = @Column(name = "professor_id")),
		@AttributeOverride(name = "courseId", column = @Column(name = "course_id"))
})

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Allocation {

	@Id
	private long professorId;

	@Id
	private long courseId;

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
	@JoinColumn(name = "professor_id", nullable = false, insertable = false, updatable = false)
	private Professor professor;

	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false, insertable = false, updatable = false)
	private Course course;
}
