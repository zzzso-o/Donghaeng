package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.api.request.TripCreateRequestDto;
import com.team.pj.donghang.api.request.TripUpdateRequestDto;
import com.team.pj.donghang.api.response.LastTripResponseDto;
import com.team.pj.donghang.api.response.TripResponseDto;
import com.team.pj.donghang.domain.entity.CustomUserDetails;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.service.TripService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

@Api(value = "일정 생성과 관련된 API Auth 필수" ,tags = {"schedule,plan : auth 필수"})
@RestController
@CrossOrigin("*")
@RequestMapping("/api/trip")
public class TripController {
    private final Logger log = LoggerFactory.getLogger(TripController.class);
    @Autowired
    TripService tripService;

    @PostMapping("")
    @ApiOperation(value = "일정 생성")
    @ApiResponses({
            @ApiResponse(code = 201,message = "일정이 성공적으로 생성되었습니다."),
            @ApiResponse(code = 401, message = "유효하지 않은 사용자입니다")
    })
    public ResponseEntity<?> tripScheduleCreate(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "일정 생성을 위한 정보",required = true) @RequestBody TripCreateRequestDto tripCreateRequestDto
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.getDetails();

        User user = customUserDetails.getUser();
        tripService.createTrip(user, tripCreateRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getTodayTrip")
    @ApiOperation("오늘이 포함된 일정 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "장소 detail 정보"),
            @ApiResponse(code = 401, message = "유효하지 않은 사용자입니다")
    })
    public ResponseEntity<?> getTodayTrip(
            @ApiIgnore Authentication authentication
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getDetails();

        TripResponseDto responseDto = tripService.getTodayTrip(customUserDetails.getUser().getUserNo());

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/getDetailPlace")
    @Operation(summary = "장소 상세 정보들을 반환 리스트로 반환하게 함. " ,
            description ="data 형식이 각각 다름\n"+"종류는 shopping : 1, culture :15, festival :226, leisure : 13, restaurant : 2, tourist :5"
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "성공적으로 반환하였습니다."),
            @ApiResponse(code = 400, message = "같은 타입인 데이터들로 보내주세요")
    })
    public ResponseEntity<List<?>> getPlaceDetail(
            @ApiParam(value = "장소 추천을 위한 commonNo 리스트" ,required = true)Long commonNo,
            @ApiParam(value = "장소 추천을 위한 category ",example = "culture",required = true)String category){

        List<?> result = tripService.getPlaceDetail(commonNo,category);
        if(result.size() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
    @GetMapping("/getMyTripList")
    @ApiOperation(value = "내 일정 리스트")
    @ApiResponses({
            @ApiResponse(code=200, message = "일정 리스드를 정상적으로 반환했습니다."),
            @ApiResponse(code = 204, message = "일정이 없습니다 생성해주세요!")
    })
    public ResponseEntity<?> getTripList(
            @ApiIgnore  Authentication authentication
    ){
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();

        User user = userDetails.getUser();
        List<TripResponseDto> list = tripService.getUserTripList(user.getUserNo());
        if(list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.status(200).body(list);
        }
    }

    @GetMapping("/getMyLastTripList")
    @ApiOperation(value = "내 과거 여행 일정 리스트")
    @ApiResponses({
            @ApiResponse(code=200, message = "일정 리스드를 정상적으로 반환했습니다."),
            @ApiResponse(code = 204, message = "일정이 없습니다 생성해주세요!")
    })
    public ResponseEntity<?> getLatTripList(
            @ApiIgnore Authentication authentication
    )throws Exception {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();
        User user = userDetails.getUser();
        List<LastTripResponseDto> list = tripService.getUserLastTripList(user.getUserNo());

        if(list.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.status(200).body(list);
        }
    }

    @GetMapping("")
    @ApiOperation(value = "진행중이거나 / 미래 특정 일정 1개")
    @ApiResponses({
            @ApiResponse(code=200, message = "일정을 정상적으로 반환했습니다."),
            @ApiResponse(code = 404, message = "없는 일정입니다.")
    })
    public ResponseEntity<?> getMyOneTrip(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "갖고올 일정 번호",required = true) Long tripNo) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        TripResponseDto result = tripService.getUserTrip(userDetails.getUser().getUserNo(),tripNo);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return ResponseEntity.status(200).body(result);
        }
    }
    @GetMapping("/past")
    @ApiOperation(value = "과거 특정 일정 1개")
    @ApiResponses({
            @ApiResponse(code=200, message = "일정을 정상적으로 반환했습니다."),
            @ApiResponse(code = 404, message = "없는 일정입니다.")
    })
    public ResponseEntity<?> getMyLastOneTrip(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "갖고올 일정 번호",required = true)Long tripNo
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        LastTripResponseDto result = tripService.getUserPastOneTrip(userDetails.getUser().getUserNo(),tripNo);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else{
            return ResponseEntity.status(200).body(result);
        }
    }

    //궁금한 사항 여행 중에도 업데이트 가능한지
    //여행 끝나고 여행 기록 삭제 되는지 삭제될때, 이미지 삭제 되는지
    @PutMapping("")
    @ApiOperation(value = "내 일정 수정")
    @ApiResponses({
            @ApiResponse(code = 200,message = "일정이 잘 수정되었습니다"),
            @ApiResponse(code = 400,message = "잘못된 요청입니다.")
    })
    public ResponseEntity<?> updateTrip(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "일정 수정을 위한 정보",required = true) @RequestBody TripUpdateRequestDto tripUpdateRequestDto
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }
        //수정해야함.
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        boolean result = tripService.updateTrip(userDetails.getUser(),tripUpdateRequestDto);
        if(result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tripNo}")
    @ApiOperation(value = "내 일정 1개 삭제")
    @ApiResponses({
            @ApiResponse(code = 200,message = "일정이 잘 삭제되었습니다."),
            @ApiResponse(code = 404,message = "일정이 없습니다")
    })
    public ResponseEntity<?> deleteTrip(
            @ApiIgnore Authentication authentication,
            @PathVariable(value = "tripNo", required = true)Long tripNo
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }
        //수정해야함.
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        boolean flag = tripService.deleteTrip(userDetails.getUser(),tripNo);
        if(flag){
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
