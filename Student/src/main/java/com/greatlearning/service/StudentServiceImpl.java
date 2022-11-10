package com.greatlearning.service;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatlearning.entity.Student;
import com.greatlearning.entity.User;
import com.greatlearning.repository.studentRepository;

import java.util.List;

import javax.transaction.Transactional;

public abstract class StudentServiceImpl implements StudentService{
	@Autowired
	studentRepository studentRepository;
	
	@Transactional
	public List<Student> findAll()
	{
		List<Student> students=studentRepository.findAll();
		return students;
	}
	
	@Transactional
	public Student findById(int id)
	{
		Student student = new Student();
		student=studentRepository.findById(id).get();
		return student;
		
	}
	@Transactional
	public void save(Student thestudent) {
	
	studentRepository.save(thestudent);
	}
	
	@Transactional
	
	public void deleteById(int id) {
		studentRepository.deleteById(id);
	}
	@Override
	public List<Student> searchByfirstName(String firstName) {
		// TODO Auto-generated method stub
		return studentRepository.findByfirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Student> sortByfirstNameAsc() {
		// TODO Auto-generated method stub
		return studentRepository.findAllByOrderByfirstNameAsc();
	}

	@Override

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

}
}
