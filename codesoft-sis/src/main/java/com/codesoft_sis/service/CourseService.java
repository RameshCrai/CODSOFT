package com.codesoft_sis.service;

import java.util.List;

import com.codesoft_sis.entity.Course;

public interface CourseService {

	public boolean saveCourse(Course course);

	public List<Course> getCourse(String code);

	public List<Course> listOfCourse();

}
