package com.team.pj.donghang.api.response;

import com.team.pj.donghang.domain.entity.PlaceCommon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("Last Trip Response Dto")
public class LastTripResponseDto {
    @ApiModelProperty(name ="trip 일정 번호")
    private Long tripNo;

    @ApiModelProperty(name ="유저 번호")
    private Long userNo;

    @ApiModelProperty(name ="일정 시작 날짜",example = "20220901")
    String startDate;
    @ApiModelProperty(name ="일정 끝나는 날짜",example = "20220903")
    String endDate;
    @ApiModelProperty(name ="일정 제목")
    String tripName;

    @ApiModelProperty(name = "일정 장소 리스트")
    private List<PlaceCommon> placeList;
    @ApiModelProperty(name = "이미지 url 리스트")
    private List<String> imageList;
    @ApiModelProperty(name = "썸네일")
    private String thumbnail;
}
