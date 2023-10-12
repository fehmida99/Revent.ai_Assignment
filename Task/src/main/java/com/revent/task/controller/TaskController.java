package com.revent.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revent.task.entity.Taskk;
import com.revent.task.exception.TaskkException;
import com.revent.task.service.TaskService;

@CrossOrigin
@RestController
public class TaskController {

	@Autowired(required = true)
	private TaskService taskService;

	// Save operation
	@PostMapping("/tasks")
	public ResponseEntity<Taskk> saveTask(@Validated @RequestBody Taskk task) throws TaskkException {
		Taskk tk = taskService.saveTask(task);
		return new ResponseEntity<>(tk,HttpStatus.CREATED);
	}

	// Read operation
	@GetMapping("/tasks")
	public ResponseEntity<List<Taskk>> fetchTaskList() throws TaskkException {
		List<Taskk> li = taskService.fetchTaskList();
		return new ResponseEntity<>(li,HttpStatus.OK);
	}

	// Update operation
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Taskk> updateTask(@RequestBody Taskk task, @PathVariable("id") Long taskId) throws TaskkException {
		Taskk tk = taskService.updateTask(task, taskId);
		return new ResponseEntity<>(tk,HttpStatus.OK);
	}

	// Delete operation
	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable("id") Long taskId) throws TaskkException {
		 taskService.deleteTaskById(taskId);
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.ACCEPTED) ;
	}

}
