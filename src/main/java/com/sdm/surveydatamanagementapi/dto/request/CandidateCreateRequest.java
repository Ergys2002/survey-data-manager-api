package com.sdm.surveydatamanagementapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CandidateCreateRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Please provide a valid phone number")
    private String phoneNumber;
}
