package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.UserLoginRequestDto;

public interface AuthService {
    String login(UserLoginRequestDto userLoginRequestDto);

    void checkPassword(String rawPassword, String encodedPassword);

    String resolveToken(String token);

}
