package com.codesoft_sis.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesoft_sis.entity.RegisterCourse;
import com.codesoft_sis.entity.Student;
import com.codesoft_sis.repository.RegisterCourseRepository;
import com.codesoft_sis.repository.StudentRepository;
import com.codesoft_sis.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private RegisterCourseRepository registerCourseRepo;

	@Override
	public boolean saveStudent(Student student) {
		try {
			this.studentRepo.save(student);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Student loginByStudent(String name) {
		Student student = this.studentRepo.findByName(name);
		return student;
	}

	@Override
	public boolean saveRegisterCourse(RegisterCourse course) {
		try {
			this.registerCourseRepo.save(course);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RegisterCourse> getAllRegisterCourse(int id) {
	    return this.registerCourseRepo.findByStudentId(id);
	}

	@Override
	public void deleteRegisterCourse(String code) {
		this.registerCourseRepo.deleteByCourseCode(code);
	}


}
