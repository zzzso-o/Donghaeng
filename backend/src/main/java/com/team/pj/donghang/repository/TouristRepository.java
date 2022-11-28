package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.LeisureDetail;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import com.team.pj.donghang.domain.entity.TouristSpotDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<TouristSpotDetail,Long> {
//    TouristSpotDetail findByCommonNo(Long commonNo);
    TouristSpotDetail findByCommon(PlaceCommon common);
}
