package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TouristSpotDetailDto {

    @JsonProperty(value = "accom_count")
    String accomCount;

    @JsonProperty(value = "chk_creditcard")
    private String chkCreditcard;

    @JsonProperty(value = "chk_pet")
    private String chkPet;

    @JsonProperty(value = "heritage1")
    private String heritage1;

    @JsonProperty(value = "heritage2")
    private String heritage2;

    @JsonProperty(value = "heritage3")
    private String heritage3;

    @JsonProperty(value = "open_date")
    private String openDate;

    @JsonProperty(value = "parking")
    private String parking;

    @JsonProperty(value = "rest_date")
    private String restDate;

    @JsonProperty(value = "use_season")
    private String useSeason;

    @JsonProperty(value = "use_time")
    private String useTime;

    @Builder
    public TouristSpotDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String accomCount, String chkCreditcard, String chkPet, String heritage1, String heritage2, String heritage3, String openDate, String parking, String restDate, String useSeason, String useTime) {
        this.accomCount = accomCount;
        this.chkCreditcard = chkCreditcard;
        this.chkPet = chkPet;
        this.heritage1 = heritage1;
        this.heritage2 = heritage2;
        this.heritage3 = heritage3;
        this.openDate = openDate;
        this.parking = parking;
        this.restDate = restDate;
        this.useSeason = useSeason;
        this.useTime = useTime;
    }
}
