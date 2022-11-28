package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.api.request.TripMissionCreateDto;
import com.team.pj.donghang.domain.dto.MissionDto;
import com.team.pj.donghang.domain.entity.*;
import com.team.pj.donghang.service.MissionService;
import com.team.pj.donghang.service.TripMissionService;
import com.team.pj.donghang.service.TripService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "미션 관리 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/mission")
public class MissionController {

    @Autowired
    TripService tripService;

    @Autowired
    MissionService missionService;

    @Autowired
    TripMissionService tripMissionService;

    @GetMapping("/trip")
    @ApiOperation(
            value = "특정 여행의 미션 목록 조회. 처음 조회 시 무작위 3개의 미션 반환한다",
            notes = "mission_category_no 0은 기본 미션, 1은 스페셜 미션, 2는 커스텀 미션이다"
    )
    public ResponseEntity<?> getMissions(
            @ApiParam(value = "찾고자 하는 여행의 trip_no")
            @RequestParam Long tripNo
    ) {
        List<MissionDto> missions = missionService.getMissions(tripNo);

        return ResponseEntity.status(HttpStatus.OK).body(missions);
    }

    @Transactional
    @PostMapping("")
    @ApiOperation(value = "커스텀 미션 추가")
    @ApiResponses({
            @ApiResponse(code = 201,message = "커스텀 미션이 추가되었습니다"),
            @ApiResponse(code = 401, message = "유효한 사용자가 아닙니다")
    })
    public ResponseEntity<?> addMission(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "미션의 내용과 trip_no", required = true)
            @RequestBody TripMissionCreateDto tripMissionCreateDto
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getDetails();
        User user = customUserDetails.getUser();

        Mission mission = Mission.builder()
                .content(tripMissionCreateDto.getContent())
                .missionCategoryNo(2L) // 2번이 커스텀 미션
                .user(user)
                .build();

        missionService.addMission(mission);

        tripMissionService.addTripMission(
                TripMission.builder()
                        .mission(mission)
                        .trip(tripService.getTripInfo(tripMissionCreateDto.getTripNo()))
                        .photoUploaded("false")
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
    }

    @Transactional
    @DeleteMapping("")
    @ApiOperation(value = "커스텀 미션 삭제")
    public ResponseEntity<?> removeMission(
            @ApiParam(value = "삭제할 mission_no")
            @RequestParam
            Long missionNo
    ) {

        tripMissionService.deleteTripMission(missionNo);
        missionService.deleteCustomMission(missionNo);

        return ResponseEntity.status(HttpStatus.OK).body("REMOVED");
    }


    @GetMapping("/refresh")
    @ApiOperation(
            value = "특정 여행의 특정 미션을 교체한다",
            notes = "missionNo에 해당하는 미션을 삭제하고, 새로운 미션을 반환한다"
    )
    public ResponseEntity<?> refreshMission(
            @RequestParam Long missionNo,
            @RequestParam Long tripNo
    ) {
        Mission result = missionService.refreshMission(missionNo, tripNo);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Transactional
    @PutMapping("/photo")
    @ApiOperation(
            value = "사진 업로드했을 때 DB 값 변경"
    )
    public ResponseEntity<?> setPhotoUploaded(
            @RequestParam Long missionNo,
            @RequestParam Long tripNo
    ) {
        tripMissionService.setPhotoUploadedTrue(missionNo, tripNo);

        return ResponseEntity.status(HttpStatus.OK).body("MODIFIED");
    }

}