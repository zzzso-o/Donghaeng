package com.team.pj.donghang.api.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("User Info Response Dto")
public class UserInfoResponseDto {
    private String id;
    private String nickname;
    private String email;
    private String profileImage;
    private String phoneNumber;
}
