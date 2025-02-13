package com.sdm.surveydatamanagementapi.repository;

import com.example.surveydatamanager.entity.Question;
import com.example.surveydatamanager.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Set<Question> findBySurvey(Survey survey);
}
