package com.sdm.surveydatamanagementapi.repository;

import com.sdm.surveydatamanagementapi.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID> {
}
