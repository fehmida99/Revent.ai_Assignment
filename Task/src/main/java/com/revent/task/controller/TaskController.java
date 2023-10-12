package com.revent.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.revent.task.service.TaskService;

@CrossOrigin
@RestController
public class TaskController {

	@Autowired(required = true)
	private TaskService taskService;

	// Save operation
	@PostMapping("/tasks")
	public Taskk saveTask(@Validated @RequestBody Taskk task) {
		return taskService.saveTask(task);
	}

	// Read operation
	@GetMapping("/tasks")
	public List<Taskk> fetchTaskList() {
		return taskService.fetchTaskList();
	}

	// Update operation
	@PutMapping("/tasks/{id}")
	public Taskk updateTask(@RequestBody Taskk task, @PathVariable("id") Long taskId) {
		return taskService.updateTask(task, taskId);
	}

	// Delete operation
	@DeleteMapping("/tasks/{id}")
	public String deleteTaskById(@PathVariable("id") Long taskId) {
		taskService.deleteTaskById(taskId);
		return "Deleted Successfully";
	}

}
