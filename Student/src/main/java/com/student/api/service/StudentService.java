package com.student.api.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.student.api.model.Student;

@Service
public class StudentService {

	@Async
	public CompletableFuture<Student> createStudent(Student student) {

		student.setStudentId(UUID.randomUUID().toString());
		return CompletableFuture.completedFuture(student);
	}
	
	@Async
	public Student getStudent(String id) {
		
		Student student = new Student();

		student.setStudentId(id);
		return student;
	}
}
