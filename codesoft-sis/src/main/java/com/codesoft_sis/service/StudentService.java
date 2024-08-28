package com.codesoft_sis.service;

import java.util.List;

import com.codesoft_sis.entity.RegisterCourse;
import com.codesoft_sis.entity.Student;

public interface StudentService {

	public boolean saveStudent(Student student);
	public Student loginByStudent(String name);
	
	public boolean saveRegisterCourse(RegisterCourse course);
	
	public List<RegisterCourse> getAllRegisterCourse(int id);
	
	public void deleteRegisterCourse(String code);

}
