package com.sdm.surveydatamanagementapi.repository;

import com.example.surveydatamanager.entity.Candidate;
import com.example.surveydatamanager.entity.CandidateSurvey;
import com.example.surveydatamanager.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateSurveyRepository extends JpaRepository<CandidateSurvey, UUID> {
    boolean existsByCandidateIdAndSurveyId(UUID candidateId, UUID surveyId);

    CandidateSurvey findByCandidateAndSurvey(Candidate candidate, Survey survey);
}
