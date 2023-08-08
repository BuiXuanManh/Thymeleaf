package com.example.sms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sms.model.Student;
import com.example.sms.service.StudentService;
@Controller
public class StudentController {
	@Qualifier("studentImplement")
	@Autowired
	private StudentService service;

	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students", service.getAll());
		return "students";
	}
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student= new Student();
		model.addAttribute("student", student);
		return "create_student";
	}
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		service.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(Model model, @PathVariable Long id) {
		model.addAttribute("student", service.findById(id));
		return "edit_student";
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
		Student s = service.findById(id);
		s.setId(id);
		s.setFirstName(student.getFirstName());
		s.setLastName(student.getLastName());
		s.setEmail(student.getEmail());
		service.saveStudent(s);
		return "redirect:/students";
	}
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
		return "redirect:/students";
	}
//	@Autowired
//	private StudentRepository repository;
//	@GetMapping("/students")
//	public List<Student> getAll() {
//		return repository.findAll();
//	}
//	@PostMapping("/students")
//	public Student createStudent(@RequestBody Student student) {
//		return repository.save(student);
//	}
//	@GetMapping("/student/{id}")
//	public ResponseEntity<Student> getStudent(@PathVariable Long id) {
//		Student s = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not exist with id= "+id));
//		return ResponseEntity.ok(s);
//	}
//	@PutMapping("/students/{id}")
//	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student ) {
//		Student s = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not exist with id= " +id));
//		s.setFirstname(student.getFirstname());
//		s.setLastname(student.getLastname());
//		s.setEmail(student.getEmail());
//		Student u = repository.save(s);
//		return ResponseEntity.ok(u);
//	}
//	@DeleteMapping("/students/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteStudent(@PathVariable Long id) {
//		Student s = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not exist with id= " +id));
//		repository.delete(s);
//		Map<String, Boolean> r= new HashMap<>();
//		r.put("deleted",Boolean.TRUE);
//		return ResponseEntity.ok(r);
//	}
}
