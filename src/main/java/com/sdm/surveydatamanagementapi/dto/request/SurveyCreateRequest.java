package com.sdm.surveydatamanagementapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Set;

@Getter
public class SurveyCreateRequest {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Topic is required")
    private String topic;
    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Questions are required")
    @Size(min = 10, max = 40, message = "Number of questions must be between 10 and 40")
    @UniqueElements(message = "All questions must be unique")
    private Set<String> questions;}
