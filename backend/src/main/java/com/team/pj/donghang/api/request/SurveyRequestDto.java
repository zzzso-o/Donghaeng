package com.team.pj.donghang.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Survey Create &Update Dto" ,description = "설문 조사 답한 결과 ")
@Builder
public class SurveyRequestDto {
    @ApiModelProperty(name = "category bit mask",example = "부산")
    private String survey_1;

    @ApiModelProperty(name = "category bit mask",example = "0,1,2,3")
    private Long survey_2;

    @ApiModelProperty(name = "category bit mask",example = "0,1")
    private Long survey_3;

    @ApiModelProperty(name = "category bit mask",example = "0,1")
    private Long survey_4;

    @ApiModelProperty(name = "category bit mask",example = "0,1")
    private Long survey_5;

    @ApiModelProperty(name = "category bit mask",example = "0,1")
    private Long survey_6;

    @ApiModelProperty(name = "category bit mask",example = "0,1,2")
    private Long survey_7;

    @ApiModelProperty(name = "category bit mask",example = "40,50,60,70,80")
    private Long survey_8;

}