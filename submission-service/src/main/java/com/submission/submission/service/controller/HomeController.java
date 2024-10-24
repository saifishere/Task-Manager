package com.submission.submission.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/submission")
    public ResponseEntity<String> dummy(){
        return new ResponseEntity<>("welcome to submission service", HttpStatus.OK);
    }

}
