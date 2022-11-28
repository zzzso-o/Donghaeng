package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserById(String id);

    boolean existsUserByEmail(String email);

    boolean existsUserByNickname(String nickname);

    User findUserById(String userId);

    User findUserByUserNo(long userNo);
}
