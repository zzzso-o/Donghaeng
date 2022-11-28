package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.dto.SurveyUrlDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<SurveyUrlDto,String> {
//    @Override
//    Optional<SurveyUrlDto> findById(String s);
}
