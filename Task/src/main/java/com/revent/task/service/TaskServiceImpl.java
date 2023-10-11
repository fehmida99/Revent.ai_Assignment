package com.revent.task.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revent.task.entity.Taskk;
import com.revent.task.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	 @Autowired
	 private TaskRepository taskRepository;

	@Override
	public Taskk saveTask(Taskk task) {
		// TODO Auto-generated method stub
		return taskRepository.save(task);
	}

	
	
	
	
	@Override
	public List<Taskk> fetchTaskList() {
		// TODO Auto-generated method stub
		  return (List<Taskk>)taskRepository.findAll();
	}

	
	
	
	
	
	@Override
	public Taskk updateTask(Taskk task, Long taskId) {
		
		 Optional<Taskk> existingTaskOptional = taskRepository.findById(taskId);  
		 
		    
		    if (existingTaskOptional.isPresent()) {
		        Taskk existingTask = existingTaskOptional.get();
		        
		        // Update fields if they are not null and not empty
		        if (task.getTaskName() != null && !task.getTaskName().isEmpty()) {
		            existingTask.setTaskName(task.getTaskName());
		        }
		        
		        if (task.getTaskDescription() != null && !task.getTaskDescription().isEmpty()) {
		            existingTask.setTaskDescription(task.getTaskDescription());
		        }
		        
		        if (task.getTaskCode() != null && !task.getTaskCode().isEmpty()) {
		            existingTask.setTaskCode(task.getTaskCode());
		        }
		        
		        return taskRepository.save(existingTask);
		    } else {
		        throw new EntityNotFoundException("Task not found with ID: " + taskId);
		    }
	}
	
	
	
	
	@Override
	public void deleteTaskById(Long taskId) {
		// TODO Auto-generated method stub
        taskRepository.deleteById(taskId);

	}

	 
}
