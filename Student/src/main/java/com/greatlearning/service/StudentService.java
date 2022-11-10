package com.greatlearning.service;
import java.util.List;

import com.greatlearning.entity.Student;
import com.greatlearning.entity.User;

public interface StudentService {
	public List<Student> findAll();
	
	public Student findById(int theId);
	
	public void save (Student thestudent);
	
	public void deleteById(int theId);

	public List<Student> searchByfirstName(String firstName);

	public List<Student> sortByfirstNameAsc();

	User saveUser(User user);

	

}
