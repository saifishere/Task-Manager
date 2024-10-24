package com.submission.submission.service.repository;

import com.submission.submission.service.modal.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    public List<Submission> findByTaskId(Long taskId);

}
