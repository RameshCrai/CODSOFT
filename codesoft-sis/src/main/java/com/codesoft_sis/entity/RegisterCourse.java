package com.codesoft_sis.entity;

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
@Table(name = "register_course")
public class RegisterCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registerCourseId;
	private String courseCode;
	private int studentId;

}
