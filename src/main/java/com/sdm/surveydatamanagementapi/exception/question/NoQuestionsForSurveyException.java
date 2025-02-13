package com.sdm.surveydatamanagementapi.exception.question;

import java.util.UUID;

public class NoQuestionsForSurveyException extends RuntimeException {
    public NoQuestionsForSurveyException(UUID surveyId) {
        super("No questions found for survey with id: " + surveyId);
    }
}
