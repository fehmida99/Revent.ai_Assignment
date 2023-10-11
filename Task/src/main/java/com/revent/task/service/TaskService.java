package com.revent.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revent.task.entity.Taskk;

//@Service
public interface TaskService {
	 // Save operation
    Taskk saveTask(Taskk task);
 
    // Read operation
    List<Taskk> fetchTaskList();
 
    // Update operation
    Taskk updateTask(Taskk task,Long taskId);
 
    // Delete operation
    void deleteTaskById(Long taskId);
	
}


