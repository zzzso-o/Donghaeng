package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.FestivalDetail;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalDetail,Long> {

    FestivalDetail findByCommon(PlaceCommon common);
}
