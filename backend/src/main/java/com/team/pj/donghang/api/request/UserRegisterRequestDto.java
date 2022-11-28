package com.team.pj.donghang.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("UserRegisterRequest")
public class UserRegisterRequestDto {
    @ApiModelProperty(name="사용자 아이디")
    String id;

    @ApiModelProperty(name="사용자 비밀번호")
    String password;

    @ApiModelProperty(name="사용자 닉네임")
    String nickname;

    @ApiModelProperty(name="사용자 이메일")
    String email;

    @ApiModelProperty(name = "사용자 연락처")
    String phoneNumber;
}
