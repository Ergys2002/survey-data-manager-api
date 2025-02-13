package com.sdm.surveydatamanagementapi.controller;

import com.example.surveydatamanager.dto.request.SurveyCreateRequest;
import com.example.surveydatamanager.service.interfaces.ISurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final ISurveyService surveyService;
    @PostMapping
    public ResponseEntity<?> createSurvey(@RequestBody @Valid SurveyCreateRequest surveyCreateRequest) {
        return surveyService.createSurvey(surveyCreateRequest);
    }

    @GetMapping
    public ResponseEntity<?> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/survey-report")
    public ResponseEntity<?> getSurveyReport(@RequestParam String surveyId) {
        return surveyService.getSurveyReport(surveyId);
    }

    @PutMapping("/add-question")
    public ResponseEntity<?> addQuestionToSurvey(@RequestParam String surveyId, @RequestBody String question) {
        return surveyService.addQuestionToSurvey(surveyId, question);
    }

    @DeleteMapping("/delete-question")
    public ResponseEntity<?> deleteQuestionFromSurvey(@RequestParam String questionId) {
        return surveyService.deleteQuestionFromSurvey(questionId);
    }
}
