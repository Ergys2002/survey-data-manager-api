package com.sdm.surveydatamanagementapi.dto.response;

import com.example.surveydatamanager.constant.PossibleAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MostFrequentAnswerResponse {
    private SurveyResponse survey;
    private PossibleAnswer answer;
    private Long totalAnswers;
    private Long frequency;
}
