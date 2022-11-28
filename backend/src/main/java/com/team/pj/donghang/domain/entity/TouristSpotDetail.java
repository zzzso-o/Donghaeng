package com.team.pj.donghang.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TouristSpotDetail {
    @Id
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_no")
    private PlaceCommon common;

    @Column(name = "accom_count")
    String accomCount;

    @Column(name = "chk_creditcard")
    private String chkCreditcard;

    @Column(name = "chk_pet")
    private String chkPet;

    @Column(name = "heritage1")
    private String heritage1;

    @Column(name = "heritage2")
    private String heritage2;

    @Column(name = "heritage3")
    private String heritage3;

    @Column(name = "open_date")
    private String openDate;

    @Column(name = "parking")
    private String parking;

    @Column(name = "rest_date")
    private String restDate;

    @Column(name = "use_season")
    private String useSeason;

    @Column(name = "use_time")
    private String useTime;


}
