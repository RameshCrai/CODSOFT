package com.codesoft_sis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesoft_sis.entity.Marks;

public interface MarksRepository extends JpaRepository<Marks, Integer>{
	
	List<Marks> findByStudentId(int studentId);

}
