package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//public class FestivalDetailDto extends PlaceCommonDto{
public class FestivalDetailDto {

    @JsonProperty(value = "start_date")
    private String startDate;

    @JsonProperty(value = "end_date")
    private String endDate;

    private String place;

    @JsonProperty(value = "festival_grade")
    private String festivalGrade;

    @JsonProperty(value = "place_info")
    private String placeInfo;

    @JsonProperty(value = "play_time")
    private String playTime;

    @JsonProperty(value = "program")
    private String program;

    @JsonProperty(value = "spend_time")
    private String spendTime;

    @JsonProperty(value = "use_time")
    private String useTime;

    @Builder
    public FestivalDetailDto(Long commonNo, String contentId, String contentTypeId, String tel, String title, String firstImage1, String firstImage2, String cat1, String cat2, String cat3, String addr1, String addr2, String mapx, String mapy, String mlevel, String areacode, String startDate, String endDate, String place, String festivalGrade, String placeInfo, String playTime, String program, String spendTime, String useTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.place = place;
        this.festivalGrade = festivalGrade;
        this.placeInfo = placeInfo;
        this.playTime = playTime;
        this.program = program;
        this.spendTime = spendTime;
        this.useTime = useTime;
    }
}
