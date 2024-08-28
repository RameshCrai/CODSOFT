package com.codesoft_sis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codesoft_sis.entity.Course;
import com.codesoft_sis.repository.CourseRepository;
import com.codesoft_sis.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public boolean saveCourse(Course course) {
		try {
			this.courseRepo.save(course);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Course> listOfCourse() {
		List<Course> getCourses = this.courseRepo.findAll();
		return getCourses;
	}

	@Override
	public List<Course> getCourse(String code) {

		return this.courseRepo.findByCourseCode(code);
	}

}
