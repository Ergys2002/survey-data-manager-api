package com.sdm.surveydatamanagementapi.service.classes;

import com.example.surveydatamanager.constant.PossibleAnswer;
import com.example.surveydatamanager.dto.request.CandidateCreateRequest;
import com.example.surveydatamanager.dto.request.TakeSurveyRequest;
import com.example.surveydatamanager.dto.response.CandidateResponse;
import com.example.surveydatamanager.dto.response.TakeSurveyResponse;
import com.example.surveydatamanager.entity.*;
import com.example.surveydatamanager.exception.candidate.CandidateNotFoundException;
import com.example.surveydatamanager.exception.candidate.ExistingCandidateByEmailException;
import com.example.surveydatamanager.exception.candidate.ExistingCandidateByPhoneNumberException;
import com.example.surveydatamanager.exception.candidate.NoCandidatesException;
import com.example.surveydatamanager.exception.survey.SurveyAlreadyTakenException;
import com.example.surveydatamanager.exception.survey.SurveyNotFoundException;
import com.example.surveydatamanager.repository.*;
import com.example.surveydatamanager.service.interfaces.ICandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CandidateService implements ICandidateService {
    private final CandidateRepository candidateRepository;
    private final SurveyRepository surveyRepository;
    private final CandidateSurveyRepository candidateSurveyRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Override
    public ResponseEntity<?> createCandidate(CandidateCreateRequest request) {
        if (candidateRepository.existsByEmail(request.getEmail())) {
            throw new ExistingCandidateByEmailException(request.getEmail());
        }

        if (candidateRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new ExistingCandidateByPhoneNumberException(request.getPhoneNumber());
        }

        Candidate candidate = Candidate.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();

        Candidate savedCandidate = candidateRepository.save(candidate);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapCandidateToCandidateResponse(savedCandidate));
    }

    @Override
    public ResponseEntity<?> takeSurvey(TakeSurveyRequest request) {
        UUID candidateId = UUID.fromString(request.getCandidateId());
        UUID surveyId = UUID.fromString(request.getSurveyId());

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

        if (candidateSurveyRepository.existsByCandidateIdAndSurveyId(candidateId, surveyId)) {
            throw new SurveyAlreadyTakenException(candidateId, surveyId);
        }

        CandidateSurvey candidateSurvey = CandidateSurvey.builder()
                .candidate(candidate)
                .survey(survey)
                .build();


        CandidateSurvey savedCandidateSurvey = candidateSurveyRepository.save(candidateSurvey);

        Set<Answer> answers = new HashSet<>();
        request.getAnswers().forEach(answerRequest -> {
            Question question = questionRepository.findById(UUID.fromString(answerRequest.getQuestionId()))
                    .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(answerRequest.getQuestionId())));

            Answer answer = Answer.builder()
                    .question(question)
                    .candidateSurvey(savedCandidateSurvey)
                    .answer(PossibleAnswer.valueOf(answerRequest.getAnswer()))
                    .build();

            answers.add(answer);
        });

        answerRepository.saveAll(answers);

        checkAndRemoveQuestionsWithHighNoAnswerIfAllCandidatesAnsweredTheSurvey(survey);

        return ResponseEntity.ok(
                TakeSurveyResponse.builder()
                        .candidateSurveyId(savedCandidateSurvey.getId())
                        .candidateId(candidateId)
                        .surveyId(surveyId)
                        .submittedAt(savedCandidateSurvey.getCreatedAt())
                        .numberOfAnswers(answers.size())
                        .build()
        );
    }

    private void checkAndRemoveQuestionsWithHighNoAnswerIfAllCandidatesAnsweredTheSurvey(Survey survey) {
        Set<Question> questionsToRemove = new HashSet<>();
        int totalCandidates = survey.getCandidates().size();

        for (Question question : survey.getQuestions()) {
            long noAnswerCount = question.getAnswers().stream()
                    .filter(answer -> PossibleAnswer.NO_ANSWER.equals(answer.getAnswer()))
                    .count();

            if (noAnswerCount > totalCandidates * 0.5) {
                questionsToRemove.add(question);
            }
        }

        if (!questionsToRemove.isEmpty()) {
            for (Question question : questionsToRemove) {
                survey.getQuestions().remove(question);
                questionRepository.delete(question);
            }
        }
    }

    @Override
    public ResponseEntity<?> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.isEmpty()) {
            throw new NoCandidatesException("No candidates found");
        }

        return ResponseEntity.ok(
                candidates.stream()
                        .map(this::mapCandidateToCandidateResponse)
                        .toList()
        );
    }

    @Override
    public ResponseEntity<?> getCandidateWithMostSurveys() {
        List<Candidate> candidates = candidateRepository.findAll();

        if (candidates.isEmpty()) {
            throw new NoCandidatesException("No candidates found");
        }

        Candidate candidateWithMostSurveys = candidates.stream()
                .max(Comparator.comparingInt(candidate -> candidate.getSurveys().size()))
                .orElseThrow(() -> new NoCandidatesException("No candidates found"));

        return ResponseEntity.ok(mapCandidateToCandidateResponse(candidateWithMostSurveys));

    }
    private CandidateResponse mapCandidateToCandidateResponse(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .email(candidate.getEmail())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .phoneNumber(candidate.getPhoneNumber())
                .createdAt(candidate.getCreatedAt())
                .build();
    }
}
