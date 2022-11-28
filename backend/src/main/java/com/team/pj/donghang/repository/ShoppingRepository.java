package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.PlaceCommon;
import com.team.pj.donghang.domain.entity.ShoppingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingRepository extends JpaRepository<ShoppingDetail,Long> {
//    ShoppingDetail findById(Long common);
    ShoppingDetail findByCommon(PlaceCommon common);
//    ShoppingDetail findByCommonNo(Long commonNo);
//    ShoppingDetail findByCommonNo(Long commonNo);
}
