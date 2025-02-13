package com.sdm.surveydatamanagementapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    private String id;
    private LocalDateTime createdAt;
    private String title;
    private String topic;
    private String description;
    private int numberOfQuestions;
}
