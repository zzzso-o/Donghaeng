package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//public class LeisureDetailDto extends PlaceCommonDto{
public class LeisureDetailDto {
    @JsonProperty(value = "accom_count")
    private String accomCount;

    @JsonProperty(value = "chk_creditcard")
    private String chkCreditcard;

    @JsonProperty(value = "chk_pet")
    private String chkPet;

    @JsonProperty(value = "info_center")
    private String infoCenter;

    @JsonProperty(value = "open_period")
    private String openPeriod;

    @JsonProperty(value = "parking")
    private String parking;

    @JsonProperty(value = "use_time")
    private String useTime;

    @Builder

    public LeisureDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String accomCount, String chkCreditcard, String chkPet, String infoCenter, String openPeriod, String parking, String useTime) {
        this.accomCount = accomCount;
        this.chkCreditcard = chkCreditcard;
        this.chkPet = chkPet;
        this.infoCenter = infoCenter;
        this.openPeriod = openPeriod;
        this.parking = parking;
        this.useTime = useTime;
    }
}
