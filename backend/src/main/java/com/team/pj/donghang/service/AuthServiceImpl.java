package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.UserLoginRequestDto;
import com.team.pj.donghang.common.util.JwtTokenUtil;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public String login(UserLoginRequestDto userLoginRequestDto) {
        if(userLoginRequestDto.getId() == null || userLoginRequestDto.getId().equals("") ||
                userLoginRequestDto.getPassword() == null || userLoginRequestDto.getPassword().equals("")) {
            throw new IllegalArgumentException("please check email and password again");
        }

        log.debug("req id: "+userLoginRequestDto.getId()+" req password: "+userLoginRequestDto.getPassword());

        User user = userRepository.findUserById(userLoginRequestDto.getId());

        checkPassword(userLoginRequestDto.getPassword(), user.getPassword());

        return JwtTokenUtil.getToken(String.valueOf(user.getUserNo()));
    }

    @Override
    public void checkPassword(String rawPassword, String encodedPassword) {
        if(!new BCryptPasswordEncoder().matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("wrong password");
        }
    }

    @Override
    public String resolveToken(String token) {
        return null;
    }
}


