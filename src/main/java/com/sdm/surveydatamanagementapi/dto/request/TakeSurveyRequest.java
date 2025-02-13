package com.sdm.surveydatamanagementapi.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
public class TakeSurveyRequest {
    @NotBlank(message = "Candidate ID is required")
    private String candidateId;

    @NotBlank(message = "Survey ID is required")
    private String surveyId;

    @NotEmpty(message = "Answers are required")
    @UniqueElements(message = "Duplicate question answers are not allowed")
    @Size(min = 10, max = 40, message = "Number of answers must be between 10 and 40")
    @Valid
    private List<AnswerRequest> answers;

}
