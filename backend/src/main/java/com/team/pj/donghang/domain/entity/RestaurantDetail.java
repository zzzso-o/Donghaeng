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
public class RestaurantDetail  {
    @Id
    private Long id;
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_no")
    private PlaceCommon common;

    @Column(name = "chk_creditcard")
    private String chkCreditcard;

    @Column(name = "info_center")
    private String infoCenter;

    @Column(name = "fist_menu")
    private String firstMenu;
;

    @Column(name = "open_date")
    private String openDate;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "packing")
    private String packing;

    @Column(name = "parking")
    private String parking;

    @Column(name = "reservation")
    private String reservation;

    @Column(name = "rest_date")
    private String restDate;

    @Column(name = "scale")
    private String scale;

    @Column(name = "seat")
    private String seat;

    @Column(name = "smoking")
    private String smoking;

    @Column(name = "treat_menu")
    private String treatMenu;

}
