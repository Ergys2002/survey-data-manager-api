package com.sdm.surveydatamanagementapi.exception.answer;

public class NoAnswersForSurveyException extends RuntimeException {
    public NoAnswersForSurveyException(String surveyId) {
        super("No answer found for survey with id: " + surveyId);
    }
}
