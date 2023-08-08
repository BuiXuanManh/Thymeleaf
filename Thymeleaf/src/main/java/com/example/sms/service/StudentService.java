package com.example.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.sms.model.Student;
@Service
public interface StudentService {
	List<Student> getAll();
	Student saveStudent(Student student);
	Student findById(Long id);
	void deleteStudent(Long id);
}
