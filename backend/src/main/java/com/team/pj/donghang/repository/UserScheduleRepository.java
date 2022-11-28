package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScheduleRepository extends JpaRepository<User, Long> {
    User findByUserNo(Long userNo);
    User findById(String id);
}
