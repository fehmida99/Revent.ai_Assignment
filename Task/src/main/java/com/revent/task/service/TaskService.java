package com.revent.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revent.task.entity.Taskk;
import com.revent.task.exception.TaskkException;

//@Service
public interface TaskService {
	 // Save operation
    Taskk saveTask(Taskk task) throws TaskkException;
 
    // Read operation
    List<Taskk> fetchTaskList() throws TaskkException;
 
    // Update operation
    Taskk updateTask(Taskk task,Long taskId) throws TaskkException;
 
    // Delete operation
    void deleteTaskById(Long taskId) throws TaskkException;
	
}


