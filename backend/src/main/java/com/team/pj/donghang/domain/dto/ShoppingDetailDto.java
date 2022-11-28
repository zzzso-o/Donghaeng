package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//public class ShoppingDetailDto extends PlaceCommonDto{
public class ShoppingDetailDto {

    @JsonProperty(value="chk_creditcard")
    private String chkCreditcard;

    @JsonProperty(value="chk_pet")
    private String chkPet;

    @JsonProperty(value="culture_center")
    private String cultureCenter;

    @JsonProperty(value="fair_day")
    private String fairDay;

    @JsonProperty(value = "info_center")
    private String infoCenter;

    @JsonProperty(value = "open_date")
    private String openDate;

    @JsonProperty(value = "open_time")
    private String openTime;


    @JsonProperty(value = "parking")
    private String parking;

    @JsonProperty(value = "rest_date")
    private String restDate;

    @JsonProperty(value = "rest_room")
    private String restRoom;

    @JsonProperty(value = "sale_item")
    private String saleItem;

    @JsonProperty(value = "sale_item_cost")
    private String saleItemCost;

    @JsonProperty(value = "scale")
    private String scale;

    @JsonProperty(value = "shop_guide")
    private String shopGuide;

    @Builder
    public ShoppingDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String chkCreditcard, String chkPet, String cultureCenter, String fairDay, String infoCenter, String openDate, String openTime, String parking, String restDate, String restRoom, String saleItem, String saleItemCost, String scale, String shopGuide) {
        this.chkCreditcard = chkCreditcard;
        this.chkPet = chkPet;
        this.cultureCenter = cultureCenter;
        this.fairDay = fairDay;
        this.infoCenter = infoCenter;
        this.openDate = openDate;
        this.openTime = openTime;
        this.parking = parking;
        this.restDate = restDate;
        this.restRoom = restRoom;
        this.saleItem = saleItem;
        this.saleItemCost = saleItemCost;
        this.scale = scale;
        this.shopGuide = shopGuide;
    }
}
