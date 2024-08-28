package com.codesoft_sis.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codesoft_sis.entity.Course;
import com.codesoft_sis.entity.Marks;
import com.codesoft_sis.entity.MessageMaster;
import com.codesoft_sis.entity.RegisterCourse;
import com.codesoft_sis.entity.Student;
import com.codesoft_sis.service.StudentService;
import com.codesoft_sis.serviceImpl.CourseServiceImpl;
import com.codesoft_sis.serviceImpl.MarksServiceImpl;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private MarksServiceImpl marksService;

	@GetMapping("/getStudent")
	public String getStudent() {

		return "./pages/Student";
	}

	@GetMapping("/getLoginStudent")
	public String getLoginStudent() {

		return "./pages/Login";
	}

	@GetMapping("/getDashboard")
	public String getDashboard(HttpSession session, Model model) {
		Student student = (Student) session.getAttribute("student");
		if (student != null) {
			session.setAttribute("student", student);
			System.out.println("hello Session = " + student.getName());

			List<RegisterCourse> listofCourse = this.getCourses(student.getStudentId());
			List<Course> allCourses = listofCourse.stream()
					.flatMap(registerCourse -> this.getAllCourseByCouseCode(registerCourse.getCourseCode()).stream())
					.collect(Collectors.toList());

			model.addAttribute("courses", allCourses);

			List<Course> courseList = this.courseService.listOfCourse();
			model.addAttribute("courseList", courseList);

			return "./pages/Dashboard";
		}

		return "./pages/Login";
	}

	@PostMapping("/saveStudent")
	public String getStudent(@ModelAttribute Student student, Model model) {
		if (this.studentService.saveStudent(student)) {
			model.addAttribute("success", "Sudent Registration Successfuly ???");
		} else {
			model.addAttribute("error", "Sudent Registration Failed ???");
		}
		return "./pages/Student";
	}

	@PostMapping("/loginStudent")
	public String getStudent(@RequestParam("name") String name, HttpSession session, Model model) {
		Student student = this.studentService.loginByStudent(name);
		if (student != null) {
			session.setAttribute("student", student);
			System.out.println("hello Session = " + student.getName());

			model.addAttribute("student", student);

			List<RegisterCourse> listofCourse = this.getCourses(student.getStudentId());
			List<Course> allCourses = listofCourse.stream()
					.flatMap(registerCourse -> this.getAllCourseByCouseCode(registerCourse.getCourseCode()).stream())
					.collect(Collectors.toList());

			model.addAttribute("courses", allCourses);

			List<Course> courseList = this.courseService.listOfCourse();
			model.addAttribute("courseList", courseList);

			return "./pages/Dashboard";
		} else {
			model.addAttribute("error", "Sudent Registration Failed ???");
		}

		return "redirect:/pages/Login";
	}

	@GetMapping("/getApply/{courseCode}")
	public String getApplyForCourse(@PathVariable("courseCode") String courseCode, HttpSession session, Model model) {
		try {
			Student student = (Student) session.getAttribute("student");

			if (student != null) {

				RegisterCourse rCourse = new RegisterCourse();
				rCourse.setCourseCode(courseCode);
				rCourse.setStudentId(student.getStudentId());

				if (this.studentService.saveRegisterCourse(rCourse)) {
					List<RegisterCourse> listofCourse = this.getCourses(student.getStudentId());
					List<Course> allCourses = listofCourse.stream().flatMap(
							registerCourse -> this.getAllCourseByCouseCode(registerCourse.getCourseCode()).stream())
							.collect(Collectors.toList());

					model.addAttribute("courses", allCourses);
					session.setAttribute("mesg", new MessageMaster("Applied Successfully", "alert-success"));
				}

				return "redirect:/getDashboard";
			} else {
				session.setAttribute("mesg", new MessageMaster("Failed", "alert-danger"));
				return "redirect:/getLoginStudent";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/getLoginStudent";
	}

	@GetMapping("/deleteRegisterCourse/{code}")
	@Transactional
	public String DropCourse(@PathVariable("code") String code, HttpSession session) {
		try {
			this.studentService.deleteRegisterCourse(code);
			session.setAttribute("mesg", new MessageMaster("Deleted Successfully", "alert-success"));

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("mesg", new MessageMaster("Failed To Delete", "alert-danger"));
		}

		return "redirect:/getDashboard";
	}

//	addMarsk
	@PostMapping("/addStudentMarks")
	public String getCourseCode(@ModelAttribute Marks marks, HttpSession session) {
		Student student = (Student) session.getAttribute("student");
		if (student != null) {

			marks.setStudentId(student.getStudentId());
			this.marksService.saveMarks(marks);

			session.setAttribute("mesg", new MessageMaster("Marks Add Successfully", "alert-success"));
		} else {
			session.setAttribute("mesg", new MessageMaster("Failed To Add Marks", "alert-danger"));
		}
		return "redirect:/getDashboard";

	}

	@GetMapping("/getGradesheet")
	public String getStudentGrade(HttpSession sesion, Model model) {
		Student student = (Student) sesion.getAttribute("student");
		if (student != null) {
			List<Marks> listofMarks = this.marksService.listOfMarks(student.getStudentId());

			model.addAttribute("markslist", listofMarks);
			int sumOfMarks = listofMarks.stream().mapToInt(Marks::getMarks).sum();
			model.addAttribute("totalSum", sumOfMarks);

			float percentage = ((float) sumOfMarks / 500) * 100;
			model.addAttribute("percentage", percentage);
			
			model.addAttribute("student", student);

			return "./pages/Grade";
		}

		return "./pages/Login";
	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.removeAttribute("student");
		return "./pages/Login";
	}

	public List<RegisterCourse> getCourses(int id) {
		return this.studentService.getAllRegisterCourse(id);
	}

	public List<Course> getAllCourseByCouseCode(String code) {
		return this.courseService.getCourse(code);
	}

}
