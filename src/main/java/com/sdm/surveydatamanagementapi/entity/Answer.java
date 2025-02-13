package com.sdm.surveydatamanagementapi.entity;

import com.sdm.surveydatamanagementapi.constant.PossibleAnswer;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Answer extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "candidate_survey_id", referencedColumnName = "id")
    private CandidateSurvey candidateSurvey;

    @Enumerated(EnumType.STRING)
    private PossibleAnswer answer;
}
