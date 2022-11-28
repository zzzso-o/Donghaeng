package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.entity.TripMission;
import com.team.pj.donghang.repository.MissionRepository;
import com.team.pj.donghang.repository.TripMissionRepository;
import com.team.pj.donghang.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TripMissionServiceImpl implements TripMissionService {

    @Autowired
    TripMissionRepository tripMissionRepository;

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    TripRepository tripRepository;

    @Override
    public void addTripMission(TripMission tripMission) {
        tripMissionRepository.save(tripMission);
    }

    @Override
    public void deleteTripMission(Long missionNo) {
        tripMissionRepository.removeTripMissionByMission_MissionNo(missionNo);
    }

    @Override
    public void setPhotoUploadedTrue(Long missionNo, Long tripNo) {
        TripMission tripMission = tripMissionRepository.getTripMissionsByMissionAndTrip(
                missionRepository.findMissionByMissionNo(missionNo),
                tripRepository.findByTripNo(tripNo)
        );

        log.debug("trip mission: {}", tripMission);

        tripMissionRepository.save(
                TripMission.builder()
                        .tripMissionNo(tripMission.getTripMissionNo())
                        .trip(tripMission.getTrip())
                        .mission(tripMission.getMission())
                        .photoUploaded("true")
                        .build()
        );
    }
}