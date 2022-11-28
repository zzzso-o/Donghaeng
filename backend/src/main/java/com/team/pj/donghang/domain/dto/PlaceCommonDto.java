package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
public class PlaceCommonDto {
    @JsonProperty(value="common_no")
    public Long commonNo;

    @JsonProperty(value="content_id")
    public String contentId;

    @JsonProperty(value="content_type_id")
    public String contentTypeId;

    public String tel;

    public String title;

    @JsonProperty(value="first_image1")
    public String firstImage1;

    @JsonProperty(value="first_image2")
    public String firstImage2;

    public String cat1;
    public String cat2;
    public String cat3;
    public String addr1;
    public String addr2;
    public String mapx;
    public String mapy;
    public String mlevel;

    @JsonProperty(value = "area_code")
    public String areacode;

}
