package com.sdm.surveydatamanagementapi.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IAnswerService {
    ResponseEntity<?> getMostFrequentAnswerForSurvey(String surveyId);

    ResponseEntity<?> getAnswersByCandidateOnSurvey(String surveyId, String candidateId);
}
