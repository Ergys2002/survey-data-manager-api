package com.sdm.surveydatamanagementapi.config;

import com.example.surveydatamanager.dto.response.ErrorResponse;
import com.example.surveydatamanager.exception.answer.NoAnswersForSurveyException;
import com.example.surveydatamanager.exception.candidate.CandidateNotFoundException;
import com.example.surveydatamanager.exception.candidate.ExistingCandidateByEmailException;
import com.example.surveydatamanager.exception.candidate.ExistingCandidateByPhoneNumberException;
import com.example.surveydatamanager.exception.candidate.NoCandidatesException;
import com.example.surveydatamanager.exception.candidateSurvey.CandidateSurveyNotFoundException;
import com.example.surveydatamanager.exception.question.InvalidQuestionException;
import com.example.surveydatamanager.exception.question.NoQuestionsForSurveyException;
import com.example.surveydatamanager.exception.question.NotAllQuestionsSavedException;
import com.example.surveydatamanager.exception.question.QuestionNotFoundException;
import com.example.surveydatamanager.exception.survey.NoSurveysException;
import com.example.surveydatamanager.exception.survey.SurveyAlreadyTakenException;
import com.example.surveydatamanager.exception.survey.SurveyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistingCandidateByEmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleExistingCandidateByEmailException(ExistingCandidateByEmailException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(ExistingCandidateByPhoneNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleExistingCandidateByPhoneNumberException(ExistingCandidateByPhoneNumberException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCandidateNotFoundException(CandidateNotFoundException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(SurveyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleSurveyNotFoundException(SurveyNotFoundException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(NotAllQuestionsSavedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleNotAllQuestionsSavedException(NotAllQuestionsSavedException e, WebRequest request){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(NoCandidatesException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoCandidatesFoundException(NoCandidatesException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(SurveyAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleSurveyAlreadyTakenException(SurveyAlreadyTakenException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.CONFLICT.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(NoSurveysException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoSurveysException(NoSurveysException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(NoQuestionsForSurveyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoQuestionsForSurveyException(NoQuestionsForSurveyException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(NoAnswersForSurveyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNoAnswersForSurveyException(NoAnswersForSurveyException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(InvalidQuestionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleInvalidQuestionException(InvalidQuestionException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(CandidateSurveyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleCandidateSurveyNotFoundException(CandidateSurveyNotFoundException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    public ResponseEntity<ErrorResponse> handleQuestionNotFoundException(QuestionNotFoundException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException e, WebRequest request){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Validation failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .errors(errors)
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .timestamp(LocalDateTime.now())
                        .path(((ServletWebRequest) request).getRequest().getRequestURI())
                        .build());
    }
}
