package com.team.pj.donghang.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * common _no : a뭐뭐다?
 */

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShoppingDetail {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "common_no")
    private PlaceCommon common;


    @Column(name="chk_creditcard")
    private String chkCreditcard;

    @Column(name="chk_pet")
    private String chkPet;

    @Column(name="culture_center")
    private String cultureCenter;

    @Column(name="fair_day")
    private String fairDay;

    @Column(name = "info_center")
    private String infoCenter;

    @Column(name = "open_date")
    private String openDate;

    @Column(name = "open_time")
    private String openTime;


    @Column(name = "parking")
    private String parking;

    @Column(name = "rest_date")
    private String restDate;

    @Column(name = "rest_room")
    private String restRoom;

    @Column(name = "sale_item")
    private String saleItem;

    @Column(name = "sale_item_cost")
    private String saleItemCost;

    @Column(name = "scale")
    private String scale;

    @Column(name = "shop_guide")
    private String shopGuide;


}
