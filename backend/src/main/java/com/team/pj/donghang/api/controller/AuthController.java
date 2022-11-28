package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.api.request.UserLoginRequestDto;
import com.team.pj.donghang.service.AuthService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(value = "사용자 인증 관련 API")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("")
    @ApiOperation(value = "로그인", notes = "로컬 스토리지에 토큰을 저장한 후 로그인이 필요한 request의 경우 header에 \"Authorization\" : \"access-token\"을 담아 사용하면 됨")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공", response = UserLoginRequestDto.class),
            @ApiResponse(code = 401, message = "이메일 또는 비밀번호가 공백", response = UserLoginRequestDto.class),
    })
    public ResponseEntity<?> login(
            @RequestBody
            @ApiParam(value = "아이디, 비밀번호", required = true)
            UserLoginRequestDto userLoginRequestDto
    ) {
        try {
            log.debug("user login request: {}", userLoginRequestDto.toString());
            String token = authService.login(userLoginRequestDto);
            log.debug("auth token: "+token);

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }
}

