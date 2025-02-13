package com.sdm.surveydatamanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Question extends BaseEntity{
    private String content;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;
}
