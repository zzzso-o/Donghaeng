package com.team.pj.donghang.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Trip Create register Dto" ,description = "일정 제목 , 일정 시작 날짜, 일정 끝날짜")
@Builder

public class TripCreateRequestDto {
    @ApiModelProperty(name = "일정 제목",example = "2022 엄마 아빠와 함께하는 강원도 여행")
    private String tripName;

    @ApiModelProperty(name = "일정 시작 날짜",example = "20220912")
    private String startDate;

    @ApiModelProperty(name = "일정 끝 날짜",example = "20220915")
    private String endDate;

    @ApiModelProperty(name = "일정 장소 common no list",example = "[1,2,3]")
    private Long[] commonNoList;
}
