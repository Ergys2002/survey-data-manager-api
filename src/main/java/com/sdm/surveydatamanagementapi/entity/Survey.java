package com.sdm.surveydatamanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Survey extends BaseEntity {
    private String title;
    private String topic;
    private String description;

    @OneToMany(mappedBy = "survey")
    private Set<CandidateSurvey> candidates;

    @OneToMany(mappedBy = "survey")
    private Set<Question> questions;
}
