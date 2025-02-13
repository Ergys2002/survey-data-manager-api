package com.sdm.surveydatamanagementapi.repository;


import com.sdm.surveydatamanagementapi.entity.Candidate;
import com.sdm.surveydatamanagementapi.entity.CandidateSurvey;
import com.sdm.surveydatamanagementapi.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateSurveyRepository extends JpaRepository<CandidateSurvey, UUID> {
    boolean existsByCandidateIdAndSurveyId(UUID candidateId, UUID surveyId);

    CandidateSurvey findByCandidateAndSurvey(Candidate candidate, Survey survey);
}
