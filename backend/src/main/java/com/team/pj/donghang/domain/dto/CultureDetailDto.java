package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//public class CultureDetailDto exstends PlaceCommonDto{
public class CultureDetailDto{
    @JsonProperty(value = "chk_creditcard")
    private String chkCreditcard;

    @JsonProperty(value = "chk_pet")
    private String chkPet;

    @JsonProperty(value = "parking")
    private String parking;

    @JsonProperty(value = "rest_date")
    private String restDate;

    @JsonProperty(value = "use_fee")
    private String useFee;

    @JsonProperty(value = "use_time")
    private String useTime;

    @JsonProperty(value = "scale")
    private String scale;

    @JsonProperty(value = "spend_time")
    private String spendTime;

    @Builder
    public CultureDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String chkCreditcard, String chkPet, String parking, String restDate, String useFee, String useTime, String scale, String spendTime) {
        this.chkCreditcard = chkCreditcard;
        this.chkPet = chkPet;
        this.parking = parking;
        this.restDate = restDate;
        this.useFee = useFee;
        this.useTime = useTime;
        this.scale = scale;
        this.spendTime = spendTime;
    }
}
