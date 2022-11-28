package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.PlaceCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceCommonRepository extends JpaRepository<PlaceCommon,Long> {
    PlaceCommon findByCommonNo(Long commonNo);

    List<PlaceCommon> findPlaceCommonByMapxBetweenAndMapyBetweenAndContentTypeIdIsNot(String mapx, String mapx2, String mapy, String mapy2, String contentTypeId);
    List<PlaceCommon> findPlaceCommonByMapxBetweenAndMapyBetweenAndContentTypeIdIs(String mapx, String mapx2, String mapy, String mapy2, String contentTypeId);
}
