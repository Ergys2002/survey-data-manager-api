package com.sdm.surveydatamanagementapi.service.interfaces;

import com.example.surveydatamanager.dto.request.SurveyCreateRequest;
import org.springframework.http.ResponseEntity;

public interface ISurveyService {
    ResponseEntity<?> createSurvey(SurveyCreateRequest surveyCreateRequest);

    ResponseEntity<?> getAllSurveys();

    ResponseEntity<?> getSurveyReport(String surveyId);

    ResponseEntity<?> addQuestionToSurvey(String surveyId, String question);

    ResponseEntity<?> deleteQuestionFromSurvey(String questionId);
}
