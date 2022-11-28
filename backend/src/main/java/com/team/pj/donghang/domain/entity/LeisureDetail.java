package com.team.pj.donghang.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeisureDetail {
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

    @Column(name = "info_center")
    private String infoCenter;

    @Column(name = "open_period")
    private String openPeriod;

    @Column(name = "parking")
    private String parking;

    @Column(name = "use_time")
    private String useTime;


}
