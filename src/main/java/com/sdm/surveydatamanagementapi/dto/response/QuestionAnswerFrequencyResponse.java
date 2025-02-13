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
public class QuestionAnswerFrequencyResponse {
    private String question;
    private PossibleAnswer answer;
    private Long frequency;
}
