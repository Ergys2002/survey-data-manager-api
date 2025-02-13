package com.sdm.surveydatamanagementapi.exception.candidate;

public class ExistingCandidateByPhoneNumberException extends RuntimeException{
    public ExistingCandidateByPhoneNumberException(String phoneNumber) {
        super("Candidate with phone number " + phoneNumber + " already exists");
    }
}
