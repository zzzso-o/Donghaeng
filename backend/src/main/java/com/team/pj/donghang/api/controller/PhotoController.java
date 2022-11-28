package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.domain.entity.CustomUserDetails;
import com.team.pj.donghang.domain.entity.Trip;
import com.team.pj.donghang.service.PhotoService;
import com.team.pj.donghang.service.TripService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
@Api(value = "사진 업로드와 관련된 API  Auth 필수" ,tags = {"photo : auth 필"})
@RestController
@CrossOrigin("*")
@RequestMapping("/upload")
public class PhotoController {
    private final Logger log = LoggerFactory.getLogger(PhotoController.class);
    @Autowired
    PhotoService photoService;
    @Autowired
    TripService tripService;

    @PostMapping("/trip")
    @ApiOperation(value = "일정(미션) 사진 1개 업로드하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "사진이 성공적으로 업로드 되었습니다."),
            @ApiResponse(code = 401, message = "유효한 사용자가 아닙니다")
    })
    public ResponseEntity<?> uploadTripPhoto(
            @ApiIgnore Authentication authentication,
            @RequestParam("images")MultipartFile multipartFile,
            @RequestParam("tripNo")Long tripNo
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();

        Trip trip = tripService.getTripInfo(tripNo);

        String result = photoService.uploadTripPhoto(userDetails.getUser(),trip,multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/userProfile")
    @ApiOperation(value = "프로필 사진 업로드하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "프로필 사진이 성공적으로 업로드 되었습니다."),
            @ApiResponse(code = 401, message = "유효한 사용자가 아닙니다")
    })
    public ResponseEntity<?> uploadProfileImage(
            @ApiIgnore Authentication authentication,
            @RequestParam("profileImage")MultipartFile multipartFile
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();

        String result = photoService.createProfileImage(userDetails.getUser(),multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/userProfile")
    @ApiOperation(value = "프로필 이미지 수정하기")
    @ApiResponses({
            @ApiResponse(code = 201,message = "사진이 성공적으로 수정 되었습니다."),
            @ApiResponse(code = 401, message = "유효한 사용자가 아닙니다")
    })
    public ResponseEntity<?> updateProfileImage(
            @ApiIgnore Authentication authentication,
            @RequestParam("profileImage")MultipartFile multipartFile
    ){
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();

        String result = photoService.updateProfileImg(userDetails.getUser(),multipartFile);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/getTripPhotoList")
    @ApiOperation(value = "여행기록 사진 리스트 가져오기")
    @ApiResponses({
            @ApiResponse(code = 201,message = "사진들이 성공적으로 반환 되었습니다."),
            @ApiResponse(code = 400,message = "사진 정보들이 없습니다."),
            @ApiResponse(code = 401, message = "유효한 사용자가 아닙니다")
    })
    public ResponseEntity<?> getPhotoList(
            @ApiIgnore Authentication authentication,
            @ApiParam(value = "여행기록 사진들 가져오기", required = true)
            @RequestParam(value = "tripNo", required = true) Long tripNo
    ) {
//        if(authentication == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
//        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();

        Trip trip = tripService.getTripInfo(tripNo);
        List<String> photoUrlList = photoService.getImageUrlList(trip);

        if(photoUrlList == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(photoUrlList);
        }
    }
}
