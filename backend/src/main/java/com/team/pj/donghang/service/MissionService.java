package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.dto.MissionDto;
import com.team.pj.donghang.domain.entity.Mission;
import com.team.pj.donghang.domain.entity.Trip;
import com.team.pj.donghang.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MissionService {
    // 해당 미션 정보 조회
    Mission getMissionInfo(Long missionNo);

    // 커스텀 미션 정보 DB에 추가
    void addMission(Mission mission);

    // mission_no를 통해 커스텀 미션 정보 DB에서 삭제
    void deleteCustomMission(Long missionNo);

    /* 미션 새로고침 (현재 여행-미션에서 삭제 + 미션 새로 전달)
     1. 전체 일반 미션 조회
     2. 여행-미션 조회
     3. 전체 일반 미션에서 여행-미션 제외
     4. 그 중 하나 무작위 추출
     5. 무작위 추출한 미션 저장
     6. 지우고자 하는 미션 삭제
     */
    Mission refreshMission(Long missionNo, Long tripNo);

    /* 미션 조회 (초기 미션 고려)
     if 오늘 여행의 미션 개수가 0개:
        3개 무작위로 DB에서 조회 후 저장, front로 반환
     else: 오늘 여행의 미션 개수가 0개가 아니라면
        DB에서 조회 후 front로 반환
     */
    List<MissionDto> getMissions(Long tripNo);
}