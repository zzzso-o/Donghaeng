package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.LeisureDetail;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeisureRepository extends JpaRepository<LeisureDetail,Long> {
//    LeisureDetail findByCommonNo(Long commonNo);
    LeisureDetail findByCommon(PlaceCommon common);
}
