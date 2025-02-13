package com.sdm.surveydatamanagementapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AnswerRequest {
    @NotBlank(message = "Question ID is required")
    private String questionId;

    @NotNull(message = "Answer is required")
    @Pattern(
            regexp = "^(AGREE|SLIGHTLY_AGREE|SLIGHTLY_DISAGREE|DISAGREE|NO_ANSWER)$",
            message = "Invalid answer value. Must be one of: AGREE, SLIGHTLY_AGREE, SLIGHTLY_DISAGREE, DISAGREE, NO_ANSWER"
    )
    private String answer;
}
