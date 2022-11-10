package com.greatlearning.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.entity.Student;

public interface studentRepository extends JpaRepository<Student,Integer> {

	List<Student> findByfirstNameContainsAllIgnoreCase(String firstName);

	List<Student> findAllByOrderByfirstNameAsc();

}
