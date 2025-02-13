package com.sdm.surveydatamanagementapi.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IQuestionService {
    ResponseEntity<?> getQuestionsBySurvey(String surveyId);
}
