package com.sdm.surveydatamanagementapi.exception.question;

import java.util.UUID;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(UUID questionId) {
        super("Question with id " + questionId + " not found");
    }

}
