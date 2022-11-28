package com.team.pj.donghang.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name="survey")

// 문항 선정 후 수정 예정
public class Survey {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "user_no")
    private User user;

    @NotNull
    @Column(name = "survey_1")
    private String survey1;

    @NotNull
    @Column(name = "survey_2")
    private Long survey2;

    @NotNull
    @Column(name = "survey_3")
    private Long survey3;

    @NotNull
    @Column(name = "survey_4")
    private Long survey4;

    @NotNull
    @Column(name = "survey_5")
    private Long survey5;

    @NotNull
    @Column(name = "survey_6")
    private Long survey6;

    @NotNull
    @Column(name = "survey_7")
    private Long survey7;



    @NotNull
    @Column(name = "survey_8")
    private Long survey8;

    public Survey(User user, String survey1, Long survey2, Long survey3, Long survey4, Long survey5, Long survey6, Long survey7, Long survey8) {
        this.user = user;
        this.survey1 = survey1;
        this.survey2 = survey2;
        this.survey3 = survey3;
        this.survey4 = survey4;
        this.survey5 = survey5;
        this.survey6 = survey6;
        this.survey7 = survey7;
        this.survey8 = survey8;
    }


}
