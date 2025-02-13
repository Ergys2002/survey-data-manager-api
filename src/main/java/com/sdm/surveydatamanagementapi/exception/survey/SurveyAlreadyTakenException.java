package com.sdm.surveydatamanagementapi.exception.survey;

import java.util.UUID;

public class SurveyAlreadyTakenException extends RuntimeException{
    public SurveyAlreadyTakenException(UUID candidateId, UUID surveyId) {
        super("Candidate with id: " + candidateId + " has already taken survey with id: " + surveyId);
    }
}
