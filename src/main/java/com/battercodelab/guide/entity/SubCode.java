package com.battercodelab.guide.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity
public class SubCode extends Timestamped {

    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String expl;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainCode mainCode;

    @Column
    private Integer level;
}
