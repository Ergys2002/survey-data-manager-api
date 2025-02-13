package com.sdm.surveydatamanagementapi.service.classes;

import com.sdm.surveydatamanagementapi.dto.response.QuestionResponse;
import com.sdm.surveydatamanagementapi.entity.Question;
import com.sdm.surveydatamanagementapi.entity.Survey;
import com.sdm.surveydatamanagementapi.exception.question.NoQuestionsForSurveyException;
import com.sdm.surveydatamanagementapi.exception.survey.SurveyNotFoundException;
import com.sdm.surveydatamanagementapi.repository.QuestionRepository;
import com.sdm.surveydatamanagementapi.repository.SurveyRepository;
import com.sdm.surveydatamanagementapi.service.interfaces.IQuestionService;
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
