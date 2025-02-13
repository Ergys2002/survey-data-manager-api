package com.sdm.surveydatamanagementapi.exception.survey;

import java.util.UUID;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(UUID id) {
        super("Survey with id: " + id + " not found");
    }
}
