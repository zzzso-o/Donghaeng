package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.PlaceCommon;
import com.team.pj.donghang.domain.entity.RestaurantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantDetail,Long> {
//    RestaurantDetail findByCommonNo(Long commonNo);
    RestaurantDetail findByCommon(PlaceCommon common);
}
