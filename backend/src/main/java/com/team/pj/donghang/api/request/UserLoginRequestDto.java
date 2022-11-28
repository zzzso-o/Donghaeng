package com.team.pj.donghang.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("UserLoginRequest")
public class UserLoginRequestDto {
    @ApiModelProperty(name = "사용자 아이디")
    private String id;

    @ApiModelProperty(name = "사용자 비밀번호")
    private String password;
}
