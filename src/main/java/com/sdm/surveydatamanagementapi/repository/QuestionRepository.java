package com.sdm.surveydatamanagementapi.repository;

import com.sdm.surveydatamanagementapi.entity.Question;
import com.sdm.surveydatamanagementapi.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Set<Question> findBySurvey(Survey survey);
}
