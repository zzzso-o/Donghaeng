package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.Survey;
import com.team.pj.donghang.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey,Long> {
    Survey findByUser(User user);
    Survey findByUser_UserNo(Long userNo);
    Survey save(Survey survey);
}
