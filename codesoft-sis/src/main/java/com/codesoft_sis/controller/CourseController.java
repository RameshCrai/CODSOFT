package com.codesoft_sis.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codesoft_sis.entity.Course;
import com.codesoft_sis.entity.MessageMaster;
import com.codesoft_sis.serviceImpl.CourseServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class CourseController {
	@Autowired
	private CourseServiceImpl courseService;

	@GetMapping("/getCourse")
	public String getCourse(HttpSession session) {
		return "./pages/Course";
	}

	@PostMapping("/saveCourse")
	public String postCourse(@ModelAttribute("course") Course course, HttpSession session) {
		if (this.courseService.saveCourse(course)) {
			session.setAttribute("mesg", new MessageMaster("Course Add  Successfully ???", "alert-success"));
		} else {
			session.setAttribute("mesg", new MessageMaster("Course Add Failed ??? ", "alert-danger"));
		}
		return "redirect:/getCourse";
	}

	@GetMapping("/getAllCourses")
	public String getCoureses(Model model) {
		List<Course> courselist = this.courseService.listOfCourse();

		model.addAttribute("courses", courselist);

		return "./pages/CourseList";
	}

}
