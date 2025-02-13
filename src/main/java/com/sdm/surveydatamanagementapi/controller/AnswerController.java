package com.sdm.surveydatamanagementapi.controller;

import com.sdm.surveydatamanagementapi.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final IAnswerService answerService;

    @GetMapping("/most-frequent")
    public ResponseEntity<?> getMostFrequentAnswerForSurvey(@RequestParam String surveyId) {
        return answerService.getMostFrequentAnswerForSurvey(surveyId);
    }

    @GetMapping("/by-candidate-on-survey")
    public ResponseEntity<?> getAnswersByCandidateOnSurvey(@RequestParam String surveyId, @RequestParam String candidateId) {
        return answerService.getAnswersByCandidateOnSurvey(surveyId, candidateId);
    }
}
