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
public class CultureDetail  {
    @Id
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "common_no")
    private PlaceCommon common;

    @Column(name = "chk_creditcard")
    private String chkCreditcard;

    @Column(name = "chk_pet")
    private String chkPet;

    @Column(name = "parking")
    private String parking;

    @Column(name = "rest_date")
    private String restDate;

    @Column(name = "use_fee")
    private String useFee;

    @Column(name = "use_time")
    private String useTime;

    @Column(name = "scale")
    private String scale;

    @Column(name = "spend_time")
    private String spendTime;


}
