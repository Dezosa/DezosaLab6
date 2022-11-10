package com.greatlearning.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.entity.Student;
import com.greatlearning.entity.Role;
import com.greatlearning.entity.User;
import com.greatlearning.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private StudentService StudentService;

	@Autowired
	public StudentRestController(StudentService theStudentService) {
		StudentService = theStudentService;
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody Student user) {
		return StudentService.save(user);
	}

	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return ((StudentRestController) StudentService).saveRole(role);
	}

	// expose "/Students" and return list of Students
	@GetMapping("/Students")
	public List<Student> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return StudentService.findAll();
	}

	// add mapping for GET /Students/{StudentId}

	@GetMapping("/Students/{StudentId}")
	public Student getStudent(@PathVariable int StudentId) {

		Student theStudent = StudentService.findById(StudentId);

		if (theStudent == null) {
			throw new RuntimeException("Student id not found - " + StudentId);
		}

		return theStudent;
	}

	// add mapping for POST /Students - add new Student

	@PostMapping("/Students")
	public Student addStudent(@RequestBody Student theStudent) {

		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update

		theStudent.setId(0);

		StudentService.save(theStudent);

		return theStudent;
	}

	// add mapping for PUT /Students - update existing Student

	@PutMapping("/Students")
	public Student updateStudent(@RequestBody Student theStudent) {

		StudentService.save(theStudent);

		return theStudent;
	}

	// add mapping for DELETE /Students/{StudentId} - delete Student

	@DeleteMapping("/Students/{StudentId}")

	public String deleteStudent(@PathVariable int StudentId) {

		Student tempStudent = StudentService.findById(StudentId);

		// throw exception if null

		if (tempStudent == null) {
			throw new RuntimeException("Student id not found - " + StudentId);
		}

		StudentService.deleteById(StudentId);

		return "Deleted Student id - " + StudentId;
	}

	@GetMapping("/Students/search/{firstName}")
	public List<Student> searchByfirstName(@PathVariable String firstName) {
		return StudentService.searchByfirstName(firstName);
	}

	@GetMapping("/Students/sort")
	public List<Student> sortByFirstName() {
		return StudentService.sortByfirstNameAsc();
	}

}
