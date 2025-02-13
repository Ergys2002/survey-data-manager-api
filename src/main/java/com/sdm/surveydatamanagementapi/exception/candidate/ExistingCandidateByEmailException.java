package com.sdm.surveydatamanagementapi.exception.candidate;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExistingCandidateByEmailException extends RuntimeException{
    public ExistingCandidateByEmailException(String email) {
        super("Candidate with email " + email + " already exists");
    }
}
