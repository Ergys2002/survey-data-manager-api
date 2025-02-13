package com.sdm.surveydatamanagementapi.dto.response;

import com.sdm.surveydatamanagementapi.constant.PossibleAnswer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {
    private PossibleAnswer answer;
    private LocalDateTime answeredAt;
}
