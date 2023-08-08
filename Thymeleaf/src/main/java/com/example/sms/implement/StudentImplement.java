package com.example.sms.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.model.Student;
import com.example.sms.repository.StudentRepository;
import com.example.sms.service.StudentService;

@Service
public class StudentImplement implements StudentService {
	@Autowired
	private StudentRepository repository;

	
	public StudentImplement(StudentRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Student> getAll() {
		return repository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public Student findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteStudent(Long id) {
		repository.deleteById(id);
	}

}
