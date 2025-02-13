package com.sdm.surveydatamanagementapi.service.interfaces;

import com.example.surveydatamanager.dto.request.CandidateCreateRequest;
import com.example.surveydatamanager.dto.request.TakeSurveyRequest;
import org.springframework.http.ResponseEntity;

public interface ICandidateService {
    ResponseEntity<?> createCandidate(CandidateCreateRequest request);

    ResponseEntity<?> takeSurvey(TakeSurveyRequest request);

    ResponseEntity<?> getAllCandidates();

    ResponseEntity<?> getCandidateWithMostSurveys();
}
