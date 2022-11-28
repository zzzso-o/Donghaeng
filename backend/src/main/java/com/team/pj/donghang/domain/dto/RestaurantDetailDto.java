package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//public class RestaurantDetailDto extends PlaceCommonDto{
public class RestaurantDetailDto {

    @JsonProperty(value = "chk_creditcard")
    private String chkCreditcard;

    @JsonProperty(value = "info_center")
    private String infoCenter;

    @JsonProperty(value = "fist_menu")
    private String firstMenu;
    ;

    @JsonProperty(value = "open_date")
    private String openDate;

    @JsonProperty(value = "open_time")
    private String openTime;

    @JsonProperty(value = "packing")
    private String packing;

    @JsonProperty(value = "parking")
    private String parking;

    @JsonProperty(value = "reservation")
    private String reservation;

    @JsonProperty(value = "rest_date")
    private String restDate;

    @JsonProperty(value = "scale")
    private String scale;

    @JsonProperty(value = "seat")
    private String seat;

    @JsonProperty(value = "smoking")
    private String smoking;

    @JsonProperty(value = "treat_menu")
    private String treatMenu;

    @Builder
    public RestaurantDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String chkCreditcard, String infoCenter, String firstMenu, String openDate, String openTime, String packing, String parking, String reservation, String restDate, String scale, String seat, String smoking, String treatMenu) {
        this.chkCreditcard = chkCreditcard;
        this.infoCenter = infoCenter;
        this.firstMenu = firstMenu;
        this.openDate = openDate;
        this.openTime = openTime;
        this.packing = packing;
        this.parking = parking;
        this.reservation = reservation;
        this.restDate = restDate;
        this.scale = scale;
        this.seat = seat;
        this.smoking = smoking;
        this.treatMenu = treatMenu;
    }
}
