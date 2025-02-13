package com.sdm.surveydatamanagementapi.service.classes;

import com.example.surveydatamanager.dto.response.QuestionResponse;
import com.example.surveydatamanager.entity.Question;
import com.example.surveydatamanager.entity.Survey;
import com.example.surveydatamanager.exception.question.NoQuestionsForSurveyException;
import com.example.surveydatamanager.exception.survey.SurveyNotFoundException;
import com.example.surveydatamanager.repository.QuestionRepository;
import com.example.surveydatamanager.repository.SurveyRepository;
import com.example.surveydatamanager.service.interfaces.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final SurveyRepository surveyRepository;

    @Override
    public ResponseEntity<?> getQuestionsBySurvey(String surveyId) {
        Survey survey = surveyRepository.findById(UUID.fromString(surveyId))
                .orElseThrow(() -> new SurveyNotFoundException(UUID.fromString(surveyId)));

        Set<Question> questions = questionRepository.findBySurvey(survey);

        if (questions.isEmpty()){
            throw new NoQuestionsForSurveyException(UUID.fromString(surveyId));
        }

        return ResponseEntity.ok(
                questions.stream()
                        .map(this::mapQuestiontToQuestionResponse)
                        .toList()
        );
    }

    private QuestionResponse mapQuestiontToQuestionResponse(Question question){
        return QuestionResponse.builder()
                .id(question.getId())
                .content(question.getContent())
                .build();
    }
}
