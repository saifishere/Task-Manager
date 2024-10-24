package com.task.service;

import com.task.modal.Task;
import com.task.modal.TaskStatus;

import java.util.List;

public interface TaskService {

    public Task createTask(Task task, String requesterRole) throws Exception;

    public Task getTaskById(Long id) throws Exception;

    public List<Task> getAllTask(TaskStatus status);

    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception;

    public void deleteTask(Long id) throws Exception;

    public Task assignedToUser(Long userId, Long taskId) throws Exception;

    public List<Task> assignedUsersTask(Long userId, TaskStatus status);

    public Task completeTask(Long taskId) throws Exception;

}
