package com.student.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.api.model.Student;
import com.student.api.service.StudentService;

@RestController
@RequestMapping("/students/api/v1")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/getStudent/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Student getStudent(@PathVariable String id) throws InterruptedException, ExecutionException {

		Student student = this.studentService.getStudent(id);

		return student;
	}

	@RequestMapping(value = "/createStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createStudent(@Valid @RequestBody Student student, BindingResult bindingResult)
			throws InterruptedException, ExecutionException {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap();
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<List<FieldError>>(HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();

		CompletableFuture<Student> createStudent = this.studentService.createStudent(student);
		String studentId = createStudent.get().getStudentId();

		return new ResponseEntity<>("Student " + studentId + " created", HttpStatus.CREATED);
	}

}
