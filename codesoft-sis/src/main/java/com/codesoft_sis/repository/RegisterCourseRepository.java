package com.codesoft_sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesoft_sis.entity.RegisterCourse;

public interface RegisterCourseRepository extends JpaRepository<RegisterCourse, Integer> {
	List<RegisterCourse> findByStudentId(int id);
	
	void deleteByCourseCode(String code);
}
