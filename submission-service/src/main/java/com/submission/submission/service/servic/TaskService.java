package com.submission.submission.service.servic;

import com.submission.submission.service.modal.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="TASK-SERVICE", url="http://localhost:5002")
public interface TaskService {

    @GetMapping("/api/tasks/{taskId}")
    public TaskDto getTaskById(@PathVariable Long taskId, @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{taskId}/completed")
    public TaskDto completedTask(@PathVariable Long taskId) throws Exception;


}
