package com.task.service;

import com.task.modal.Task;
import com.task.modal.TaskStatus;
import com.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Task createTask(Task task, String requesterRole) throws Exception {
        if(!requesterRole.equals("ROLE_ADMIN")){
            throw new Exception("you are not allowed to create a task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(() -> new Exception("task not found with id " + id));
    }

    @Override
    public List<Task> getAllTask(TaskStatus status) {
        List<Task> allTasks = taskRepository.findAll();
        List<Task> filterTask = allTasks.stream().filter(
                task-> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filterTask;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {

        Optional<Task> optional =  taskRepository.findById(id);
        if(optional.isEmpty()){
            throw new Exception("no task corresponding to this id " +id);
        }

        Task task = optional.get();

        if(updatedTask.getTitle() != null){
            task.setTitle(updatedTask.getTitle());
        }
        if(updatedTask.getImage() != null){
            task.setImage(updatedTask.getImage());
        }
        if(updatedTask.getDescription() != null){
            task.setDescription(updatedTask.getDescription());
        }
        if(updatedTask.getStatus() != null){
            task.setStatus(updatedTask.getStatus());
        }
        if(updatedTask.getDeadline() != null){
            task.setDeadline(updatedTask.getDeadline());
        }

        return taskRepository.save(task);

    }

    @Override
    public void deleteTask(Long id) throws Exception {

        Optional<Task> optional =  taskRepository.findById(id);
        if(optional.isEmpty()){
            throw new Exception("no task corresponding to this id " +id);
        }

        taskRepository.deleteById(id);

    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {

        Task task = getTaskById(userId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.ASSIGNED);

        return taskRepository.save(task);

    }

    @Override
    public List<Task> assignedUsersTask(Long userId, TaskStatus status) {

        List<Task> allTasks = taskRepository.findByAssignedUserId(userId);

        return allTasks.stream().filter(
                task-> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());

    }

    @Override
    public Task completeTask(Long taskId) throws Exception {

        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);

    }
}
