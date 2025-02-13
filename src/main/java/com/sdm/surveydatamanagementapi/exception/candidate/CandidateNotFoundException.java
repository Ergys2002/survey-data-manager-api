package com.sdm.surveydatamanagementapi.exception.candidate;

import java.util.UUID;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(UUID id) {
        super("Candidate with id: " + id + " not found");
    }
}