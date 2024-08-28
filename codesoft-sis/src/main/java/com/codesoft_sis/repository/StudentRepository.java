package com.codesoft_sis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesoft_sis.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	Student findByName(String name);

}
