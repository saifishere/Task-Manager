package com.task.controller;

import com.task.modal.Task;
import com.task.modal.TaskStatus;
import com.task.modal.UserDto;
import com.task.service.TaskService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task createdTask = taskService.createTask(task, user.getRole());

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.getTaskById(taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getAssignedUserTask(@RequestParam(required = false)TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.assignedUsersTask(user.getId(), status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(@RequestParam(required = false)TaskStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        List<Task> tasks = taskService.getAllTask(status);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @PutMapping("/{taskId}/user/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId,
                                                 @RequestHeader("Authorization") String jwt,
                                                 @PathVariable Long userId) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(userId, taskId);

        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestBody Task utask , @PathVariable Long taskId, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.updateTask(taskId, utask, user.getId());

        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @PutMapping("/{taskId}/completed")
    public ResponseEntity<Task> completedTask(@PathVariable Long taskId) throws Exception {

        Task t = taskService.completeTask(taskId);

        return new ResponseEntity<>(t, HttpStatus.OK);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws Exception {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
