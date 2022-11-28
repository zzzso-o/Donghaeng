package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.UserModifyRequestDto;
import com.team.pj.donghang.api.request.UserRegisterRequestDto;
import com.team.pj.donghang.domain.entity.User;

public interface UserService {

    boolean isIdExist(String id); // id 중복 확인

    boolean isNickNameExist(String nickname); // 닉네임 중복 확인

    boolean isEmailExist(String email); // 이메일 중복 확인

    User createUser(UserRegisterRequestDto userRegisterRequestDto); // 사용자 회원 가입

    User modifyUser(User currentUser, UserModifyRequestDto newUser);

    User getUserByUserId(String userId); // 사용자 id로 사용자 정보 조회
    
    User getUserByUserNo(Long userno); // 사용자 userno로 사용자 정보 조회
}
