package com.codesoft_sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesoft_sis.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
//	Course findByCourseCode(String courseCode);
	
	List<Course> findAll();
	List<Course> findByCourseCode(String courseCode);
}
