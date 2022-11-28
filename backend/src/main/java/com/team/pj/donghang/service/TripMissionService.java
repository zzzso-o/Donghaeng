package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.entity.TripMission;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TripMissionService {
    // 사용자의 현재 일정에 미션 추가
    void addTripMission(TripMission tripMission);

    // mission_no를 통해 커스텀 미션 정보 DB에서 삭제
    void deleteTripMission(Long missionNo);

    // photo uploaded 되었다고 설정
    void setPhotoUploadedTrue(Long missionNo, Long tripNo);
}