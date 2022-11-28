package com.team.pj.donghang.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Survey  Result  Dto" ,description = "부모님이 설문 조사 답한 결과 값 가져오기")
@Builder
public class SurveyResponseDto {
    @ApiModelProperty(name = "1번결과",example = "부산")
    private String survey_1;

    @ApiModelProperty(name = "2번 결과",example = "0,1,2,3")
    private Long survey_2;

    @ApiModelProperty(name = "3번 결과",example = "0,1")
    private Long survey_3;

    @ApiModelProperty(name = "4번 결과",example = "0,1")
    private Long survey_4;

    @ApiModelProperty(name = "5번 결과",example = "0,1")
    private Long survey_5;

    @ApiModelProperty(name = "6번 결과",example = "0,1")
    private Long survey_6;

    @ApiModelProperty(name = "7번 결과",example = "0,1,2")
    private Long survey_7;

    @ApiModelProperty(name = "8번 결과",example = "40,50,60,70,80")
    private Long survey_8;

}