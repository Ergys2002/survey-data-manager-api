package com.sdm.surveydatamanagementapi.exception.question;

public class NotAllQuestionsSavedException extends RuntimeException {
    public NotAllQuestionsSavedException(int savedQuestions, int totalQuestions) {
        super("Not all questions saved. Saved: " + savedQuestions + ", Total: " + totalQuestions);
    }
}
