package com.submission.submission.service.servic;

import com.submission.submission.service.modal.Submission;

import java.util.List;

public interface SubmissionService {

    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception;

    public Submission getTaskSubmissionById(Long submissionId) throws Exception;

    public List<Submission> getAllTaskSubmission();

    public List<Submission> getTaskSubmissionsByTaskId(Long taskId);

    public Submission acceptDeclineSubmission(Long id, String status) throws Exception;


}
