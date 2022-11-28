package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.CultureDetail;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CultureRepository extends JpaRepository<CultureDetail,Long> {

    CultureDetail findByCommon(PlaceCommon common);
}
