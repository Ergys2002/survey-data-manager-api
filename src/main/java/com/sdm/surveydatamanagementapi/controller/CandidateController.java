package com.sdm.surveydatamanagementapi.controller;

import com.example.surveydatamanager.dto.request.CandidateCreateRequest;
import com.example.surveydatamanager.dto.request.TakeSurveyRequest;
import com.example.surveydatamanager.service.interfaces.ICandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final ICandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> createCandidate(@RequestBody @Valid CandidateCreateRequest request){
        return candidateService.createCandidate(request);
    }

    @PostMapping("/take-survey")
    public ResponseEntity<?> takeSurvey(@RequestBody @Valid TakeSurveyRequest request){
        return candidateService.takeSurvey(request);
    }

    @GetMapping
    public ResponseEntity<?> getAllCandidates(){
        return candidateService.getAllCandidates();
    }

    @GetMapping("/has-most-surveys")
    public ResponseEntity<?> getCandidateWithMostSurveys(){
        return candidateService.getCandidateWithMostSurveys();
    }
}
