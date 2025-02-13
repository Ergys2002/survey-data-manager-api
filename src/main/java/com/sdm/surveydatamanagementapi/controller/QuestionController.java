package com.sdm.surveydatamanagementapi.controller;

import com.example.surveydatamanager.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getQuestionsBySurvey(@RequestParam String surveyId) {
        return questionService.getQuestionsBySurvey(surveyId);
    }
}
