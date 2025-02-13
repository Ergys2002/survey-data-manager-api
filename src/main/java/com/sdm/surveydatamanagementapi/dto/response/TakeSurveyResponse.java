package com.sdm.surveydatamanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TakeSurveyResponse {
    private UUID candidateSurveyId;
    private UUID candidateId;
    private UUID surveyId;
    private LocalDateTime submittedAt;
    private int numberOfAnswers;
}
