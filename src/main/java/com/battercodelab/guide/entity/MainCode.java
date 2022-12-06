package com.battercodelab.guide.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MainCode extends Timestamped {

    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String expl;

    @Column(columnDefinition = "boolean default false")
    private Boolean hasLevel;
}
