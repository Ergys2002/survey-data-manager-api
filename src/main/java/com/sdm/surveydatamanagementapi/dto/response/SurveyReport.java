package com.sdm.surveydatamanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyReport {
    private SurveyResponse survey;
    List<QuestionAnswerFrequencyResponse> reports;
}
