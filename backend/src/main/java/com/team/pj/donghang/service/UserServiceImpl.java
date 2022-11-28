package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.UserModifyRequestDto;
import com.team.pj.donghang.api.request.UserRegisterRequestDto;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isIdExist(String id) {
        return userRepository.existsUserById(id);
    }

    @Override
    public boolean isNickNameExist(String nickname) {
        return userRepository.existsUserByNickname(nickname);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User createUser(UserRegisterRequestDto userRegisterRequestDto) {
        User user = User.builder()
                .id(userRegisterRequestDto.getId())
                .password(new BCryptPasswordEncoder().encode(
                        userRegisterRequestDto.getPassword()
                ))
                .nickname(userRegisterRequestDto.getNickname())
                .email(userRegisterRequestDto.getEmail())
                .phoneNumber(userRegisterRequestDto.getPhoneNumber())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User modifyUser(User currentUser, UserModifyRequestDto newUser) {
        log.debug("----modify user----");
        log.debug("current user : {}", currentUser.toString());
        log.debug("new user : {}", newUser.toString());
        User user = User.builder()
                .userNo(currentUser.getUserNo())
                .id(newUser.getId())
                .password(new BCryptPasswordEncoder().encode(
                        newUser.getPassword()
                ))
                .nickname(newUser.getNickname())
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .profileImage(currentUser.getProfileImage())
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public User getUserByUserNo(Long userno) { return userRepository.findUserByUserNo(userno); }
}
