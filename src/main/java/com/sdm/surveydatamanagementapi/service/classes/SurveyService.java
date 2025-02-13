package com.sdm.surveydatamanagementapi.service.classes;

import com.example.surveydatamanager.constant.PossibleAnswer;
import com.example.surveydatamanager.dto.request.SurveyCreateRequest;
import com.example.surveydatamanager.dto.response.QuestionAnswerFrequencyResponse;
import com.example.surveydatamanager.dto.response.QuestionResponse;
import com.example.surveydatamanager.dto.response.SurveyReport;
import com.example.surveydatamanager.dto.response.SurveyResponse;
import com.example.surveydatamanager.entity.Answer;
import com.example.surveydatamanager.entity.Question;
import com.example.surveydatamanager.entity.Survey;
import com.example.surveydatamanager.exception.question.QuestionNotFoundException;
import com.example.surveydatamanager.exception.survey.NoSurveysException;
import com.example.surveydatamanager.exception.survey.SurveyNotFoundException;
import com.example.surveydatamanager.repository.QuestionRepository;
import com.example.surveydatamanager.repository.SurveyRepository;
import com.example.surveydatamanager.service.interfaces.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService implements ISurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createSurvey(SurveyCreateRequest surveyCreateRequest) {
        Survey survey = Survey.builder()
                .title(surveyCreateRequest.getTitle())
                .topic(surveyCreateRequest.getTopic())
                .description(surveyCreateRequest.getDescription())
                .build();

        Survey savedSurvey = surveyRepository.save(survey);

        Set<String> questions = surveyCreateRequest.getQuestions();

        AtomicInteger savedQuestions = new AtomicInteger(0);

        questions.forEach(question -> {
            questionRepository.save(Question.builder()
                    .survey(savedSurvey)
                    .content(question)
                    .build());

            savedQuestions.incrementAndGet();
        });

        if (savedQuestions.get() != questions.size()) {
            throw new RuntimeException("Failed to save all questions");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SurveyResponse.builder()
                        .id(savedSurvey.getId().toString())
                        .createdAt(savedSurvey.getCreatedAt())
                        .title(savedSurvey.getTitle())
                        .description(savedSurvey.getDescription())
                        .topic(savedSurvey.getTopic())
                        .numberOfQuestions(savedQuestions.get())
                        .build());
    }

    @Override
    public ResponseEntity<?> getAllSurveys() {
        List<Survey> surveys = surveyRepository.findAll();

        if (surveys.isEmpty()) {
            throw new NoSurveysException("No surveys found");
        }

        return ResponseEntity.ok(surveys.stream()
                .map(this::mapSurveyToSurveyResponse)
                .toList());
    }

    @Override
    public ResponseEntity<?> getSurveyReport(String surveyId) {
        Survey survey = surveyRepository.findById(UUID.fromString(surveyId))
                .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(surveyId)));

        Set<Question> questions = survey.getQuestions();
        Set<Answer> answers = survey.getCandidates().stream().flatMap(candidate -> candidate.getAnswers().stream()).collect(Collectors.toSet());

        List<QuestionAnswerFrequencyResponse> frequencies = calculateAnswerFrequencies(questions, answers);

        SurveyReport surveyReport = mapToSurveyReport(survey, frequencies);

        return ResponseEntity.ok(surveyReport);
    }

    @Override
    public ResponseEntity<?> addQuestionToSurvey(String surveyId, String question) {
        Survey survey = surveyRepository.findById(UUID.fromString(surveyId))
                .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(surveyId)));

        Question savedQuestion = questionRepository.save(Question.builder()
                .survey(survey)
                .content(question)
                .build());

        return ResponseEntity.ok(QuestionResponse.builder()
                .content(savedQuestion.getContent())
                .id(savedQuestion.getId())
                .build());
    }

    @Override
    public ResponseEntity<?> deleteQuestionFromSurvey( String questionId) {
                Question question = questionRepository.findById(UUID.fromString(questionId))
                .orElseThrow(() -> new QuestionNotFoundException(UUID.fromString(questionId)));

        questionRepository.delete(question);

        return ResponseEntity.ok("Question deleted successfully");
    }

    private List<QuestionAnswerFrequencyResponse> calculateAnswerFrequencies(Set<Question> questions, Set<Answer> answers) {
        List<QuestionAnswerFrequencyResponse> frequencies = new ArrayList<>();

        for (Question question : questions) {
            Map<PossibleAnswer, Long> answerCounts = getAnswerCountsForQuestion(question, answers);

            answerCounts.forEach((answer, count) ->
                    frequencies.add(QuestionAnswerFrequencyResponse.builder()
                            .question(question.getContent())
                            .answer(answer)
                            .frequency(count)
                            .build())
            );
        }

        return frequencies;
    }

    private Map<PossibleAnswer, Long> getAnswerCountsForQuestion(Question question, Set<Answer> answers) {
        return answers.stream()
                .filter(answer -> answer.getQuestion().equals(question))
                .map(Answer::getAnswer)
                .collect(Collectors.groupingBy(
                        possibleAnswer -> possibleAnswer,
                        Collectors.counting()
                ));
    }

    private SurveyReport mapToSurveyReport(Survey survey, List<QuestionAnswerFrequencyResponse> frequencies) {
        SurveyResponse surveyResponse = SurveyResponse.builder()
                .id(survey.getId().toString())
                .topic(survey.getTopic())
                .createdAt(survey.getCreatedAt())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .numberOfQuestions(survey.getQuestions().size())
                .build();

        return SurveyReport.builder()
                .survey(surveyResponse)
                .reports(frequencies)
                .build();
    }


    private SurveyResponse mapSurveyToSurveyResponse(Survey survey) {
        return SurveyResponse.builder()
                .id(survey.getId().toString())
                .createdAt(survey.getCreatedAt())
                .title(survey.getTitle())
                .description(survey.getDescription())
                .topic(survey.getTopic())
                .numberOfQuestions(survey.getQuestions().size())
                .build();
    }
}
