package com.revent.task.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revent.task.entity.Taskk;
import com.revent.task.exception.TaskkException;
import com.revent.task.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	 @Autowired
	 private TaskRepository taskRepository;

	@Override
	public Taskk saveTask(Taskk task) throws TaskkException{
		if(taskRepository.existsById(task.getId())) {
			throw new TaskkException("Task already present by this id " + task.getId());
		}
		return taskRepository.save(task);
		
	}
	
	
	@Override
	public List<Taskk> fetchTaskList() throws TaskkException{
		  List<Taskk> newlist = (List<Taskk>) taskRepository.findAll();
		  if(newlist.isEmpty()) {
				throw new TaskkException("Task is Empty!!!");

		  }
		  return newlist;
	}

	
	
	@Override
	public Taskk updateTask(Taskk task, Long taskId) throws TaskkException{
		
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
		        throw new TaskkException("Task not found with ID: " + taskId);
		    }
	}
	
	
	
	
	@Override
	public void deleteTaskById(Long taskId) throws TaskkException {
		Optional<Taskk> task = taskRepository.findById(taskId);
		if(task.isPresent()) {
			 taskRepository.deleteById(taskId);

		}else {
			throw new TaskkException("Task is not present by this id " + taskId);
		}
       
	}

	 
}
