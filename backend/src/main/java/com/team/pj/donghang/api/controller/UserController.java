package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.api.request.UserLoginRequestDto;
import com.team.pj.donghang.api.request.UserModifyRequestDto;
import com.team.pj.donghang.api.request.UserRegisterRequestDto;
import com.team.pj.donghang.api.response.UserInfoResponseDto;
import com.team.pj.donghang.domain.entity.CustomUserDetails;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.service.AuthService;
import com.team.pj.donghang.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@CrossOrigin("*")
@Api(value = "사용자 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    @ApiOperation(value = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원 가입되었습니다"),
            @ApiResponse(code = 409, message = "중복된 값입니다")
    })
    public ResponseEntity<?> register(
            @RequestBody
            @ApiParam(value = "회원가입 필수 정보", required = true)
            UserRegisterRequestDto userRegisterRequestDto
    ) {
        log.debug("user register request body: "+ userRegisterRequestDto.toString());

        if(userService.isIdExist(userRegisterRequestDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER ID");
        }
        
        if(userService.isEmailExist(userRegisterRequestDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER EMAIL");
        }
        
        if(userService.isNickNameExist(userRegisterRequestDto.getNickname())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER NICKNAME");
        }
        
        userService.createUser(userRegisterRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @PutMapping("")
    @ApiOperation(value = "사용자 정보 수정 : 로그인 필요", notes = "변경하지 않은 값 모두 요청에 담아야 함")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정되었습니다"),
            @ApiResponse(code = 401, message = "인증되지 않은 사용자입니다")
    })
    public ResponseEntity<?> modify(
            @ApiIgnore
            Authentication authentication,
            @RequestBody
            @ApiParam(value = "사용자 정보", required = true) 
            UserModifyRequestDto userModifyRequestDto
    ) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();

        if(userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED USER");
        }

        User currentUser = userDetails.getUser();

        userService.modifyUser(currentUser, userModifyRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body("MODIFIED");
    }


    @GetMapping("/id")
    @ApiOperation(value = "아이디 중복 체크")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 아이디입니다"),
            @ApiResponse(code = 409, message = "이미 사용 중인 아이디입니다")
    })
    public ResponseEntity<?> checkIdDuplicated(
            @RequestParam
            @ApiParam(value = "사용자 아이디", required = true) String id
    ) {
        log.debug("check user id duplicated: "+id);

        if(userService.isIdExist(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER ID");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
    }

    @GetMapping("/nickname")
    @ApiOperation(value = "닉네임 중복체크")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 닉네임입니다"),
            @ApiResponse(code = 409, message = "이미 사용 중인 닉네임입니다")
    })
    public ResponseEntity<?> checkNickNameDuplicated(
            @RequestParam
            @ApiParam(value = "사용자 닉네임", required = true) String nickname
    ) {
        log.debug("check user nickname duplicated: "+nickname);

        if(userService.isNickNameExist(nickname)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER NICKNAME");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
    }

    @GetMapping("/email")
    @ApiOperation(value = "이메일 중복체크")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 이메일입니다"),
            @ApiResponse(code = 409, message = "이미 사용 중인 이메일입니다")
    })
    public ResponseEntity<?> checkEmailDuplicated(
            @RequestParam
            @ApiParam(value = "사용자 이메일", required = true) String email
    ) {
        log.debug("check email duplicated: "+email);

        if(userService.isEmailExist(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("DUPLICATED USER EMAIL");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
    }

    @GetMapping("/info")
    @ApiOperation(value = "사용자 정보 조회 : 로그인 필요")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용자 정보 조회 완료"),
            @ApiResponse(code = 401, message = "인증되지 않은 사용자")
    })
    public ResponseEntity<?> getUserInfo(@ApiIgnore Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getDetails();

        User user = customUserDetails.getUser();
        if(user == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED USER");

        UserInfoResponseDto res = UserInfoResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .phoneNumber(user.getPhoneNumber())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}

