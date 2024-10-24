package com.submission.submission.service.controller;

import com.submission.submission.service.modal.Submission;
import com.submission.submission.service.modal.UserDto;
import com.submission.submission.service.servic.SubmissionService;
import com.submission.submission.service.servic.TaskService;
import com.submission.submission.service.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{


        UserDto user = userService.getUserProfile(jwt);

        Submission submission = submissionService.getTaskSubmissionById(id);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestParam String githubLink,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{


        UserDto user = userService.getUserProfile(jwt);

        Submission submission = submissionService.submitTask(taskId, githubLink, user.getId(), jwt);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String jwt) throws Exception{

        UserDto user = userService.getUserProfile(jwt);

        List<Submission> submissions = submissionService.getAllTaskSubmission();

        return new ResponseEntity<>(submissions, HttpStatus.OK);

    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllSubmissionsByTaskId(@PathVariable Long taskId
            ,@RequestHeader("Authorization") String jwt) throws Exception{

        UserDto user = userService.getUserProfile(jwt);

        List<Submission> submissions = submissionService.getTaskSubmissionsByTaskId(taskId);

        return new ResponseEntity<>(submissions, HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDeclineSubmission(@PathVariable Long id,
                                                        @RequestParam("status") String status,
                                                        @RequestHeader("Authorization") String jwt) throws Exception{


        UserDto user = userService.getUserProfile(jwt);

        Submission submission = submissionService.acceptDeclineSubmission(id, status);

        return new ResponseEntity<>(submission, HttpStatus.OK);

    }



}
