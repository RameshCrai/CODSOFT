package com.codesoft_sis.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private int courseId;
	@Column(unique = true, nullable = false)
	private String courseCode;
	@Column(nullable = false)
	private String title;
	private String description;
	private int capacity;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime fromTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime toTime;
	private LocalDate validDate;

}
