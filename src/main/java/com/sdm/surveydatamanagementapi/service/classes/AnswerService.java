package com.sdm.surveydatamanagementapi.service.classes;

import com.example.surveydatamanager.constant.PossibleAnswer;
import com.example.surveydatamanager.dto.response.AnswerResponse;
import com.example.surveydatamanager.dto.response.MostFrequentAnswerResponse;
import com.example.surveydatamanager.dto.response.SurveyResponse;
import com.example.surveydatamanager.entity.Answer;
import com.example.surveydatamanager.entity.Candidate;
import com.example.surveydatamanager.entity.CandidateSurvey;
import com.example.surveydatamanager.entity.Survey;
import com.example.surveydatamanager.exception.answer.NoAnswersForSurveyException;
import com.example.surveydatamanager.exception.candidate.CandidateNotFoundException;
import com.example.surveydatamanager.exception.candidateSurvey.CandidateSurveyNotFoundException;
import com.example.surveydatamanager.exception.survey.SurveyNotFoundException;
import com.example.surveydatamanager.repository.CandidateRepository;
import com.example.surveydatamanager.repository.CandidateSurveyRepository;
import com.example.surveydatamanager.repository.SurveyRepository;
import com.example.surveydatamanager.service.interfaces.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {
    private final SurveyRepository surveyRepository;
    private final CandidateRepository candidateRepository;
    private final CandidateSurveyRepository candidateSurveyRepository;


    @Override
    public ResponseEntity<?> getMostFrequentAnswerForSurvey(String surveyId) {
        Survey survey = surveyRepository.findById(UUID.fromString(surveyId))
                .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(surveyId)));

        List<Answer> answersOfSurvey = survey.getCandidates().stream()
                .flatMap(candidate -> candidate.getAnswers().stream())
                .toList();

        if (answersOfSurvey.isEmpty()){
            throw new NoAnswersForSurveyException(surveyId);
        }

        long totalAnswers = answersOfSurvey.size();
        Map.Entry<PossibleAnswer, Long> mostFrequentAnswer = getMostFrequentAnswer(answersOfSurvey);

        SurveyResponse surveyResponse = SurveyResponse.builder()
                .id(survey.getId().toString())
                .createdAt(survey.getCreatedAt())
                .title(survey.getTitle())
                .topic(survey.getTopic())
                .description(survey.getDescription())
                .numberOfQuestions(survey.getQuestions().size())
                .build();

        return ResponseEntity.ok(MostFrequentAnswerResponse.builder()
                .survey(surveyResponse)
                .answer(mostFrequentAnswer.getKey())
                .totalAnswers(totalAnswers)
                .frequency(mostFrequentAnswer.getValue())
                .build());    }

    @Override
    public ResponseEntity<?> getAnswersByCandidateOnSurvey(String surveyId, String candidateId) {
        Candidate candidate = candidateRepository.findById(UUID.fromString(candidateId))
                .orElseThrow(() -> new CandidateNotFoundException(UUID.fromString(candidateId)));

        Survey survey = surveyRepository.findById(UUID.fromString(surveyId))
                .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(surveyId)));

        CandidateSurvey candidateSurvey = candidateSurveyRepository.findByCandidateAndSurvey(candidate, survey);

        if (candidateSurvey == null) {
            throw new CandidateSurveyNotFoundException("Candidate has not answered this survey");
        }

        Set<Answer> answers = candidateSurvey.getAnswers();

        if (answers.isEmpty()) {
            throw new NoAnswersForSurveyException(surveyId);
        }

        return ResponseEntity.ok(answers.stream().map(answer -> AnswerResponse.builder()
                .answer(answer.getAnswer())
                .answeredAt(answer.getCreatedAt())
                .build()));
    }

    private Map.Entry<PossibleAnswer, Long> getMostFrequentAnswer(List<Answer> answers) {
        Map<PossibleAnswer, Long> answerFrequency = new HashMap<>();
        answers.forEach(answer -> {
            answerFrequency.put(answer.getAnswer(), answerFrequency.getOrDefault(answer.getAnswer(), 0L) + 1);
        });

        return answerFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new RuntimeException("No most frequent answer found"));
    }
}
