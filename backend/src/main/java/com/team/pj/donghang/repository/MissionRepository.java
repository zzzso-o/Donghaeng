package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // missionNo로 미션 정보 조회
    Mission findMissionByMissionNo(Long missionNo);

    // missionNo로 커스텀 미션 삭제
    void deleteMissionByMissionNo(Long missionNo);

    // 미션 카테고리 번호가 x인 미션들을 조회
    List<Mission> findMissionsByMissionCategoryNoIs(Long missionCategoryNo);

    // 미션 카테고리 번호가 x가 아닌 미션들을 조회함
    List<Mission> findMissionsByMissionCategoryNoIsNot(Long missionCategoryNo);
}
