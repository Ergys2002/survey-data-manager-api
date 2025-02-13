package com.sdm.surveydatamanagementapi.service.interfaces;

import com.sdm.surveydatamanagementapi.dto.request.CandidateCreateRequest;
import com.sdm.surveydatamanagementapi.dto.request.TakeSurveyRequest;
import org.springframework.http.ResponseEntity;

public interface ICandidateService {
    ResponseEntity<?> createCandidate(CandidateCreateRequest request);

    ResponseEntity<?> takeSurvey(TakeSurveyRequest request);

    ResponseEntity<?> getAllCandidates();

    ResponseEntity<?> getCandidateWithMostSurveys();
}
