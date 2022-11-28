package com.team.pj.donghang.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FestivalDetail  {
    @Id
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_no")
    private PlaceCommon common;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    private String place;


    @Column(name = "festival_grade")
    private String festivalGrade;

    @Column(name = "place_info")
    private String placeInfo;

    @Column(name = "play_time")
    private String playTime;

    @Column(name = "program")
    private String program;

    @Column(name = "spend_time")
    private String spendTime;

    @Column(name = "use_time")
    private String useTime;

}
