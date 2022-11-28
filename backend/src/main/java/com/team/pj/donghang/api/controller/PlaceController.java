package com.team.pj.donghang.api.controller;


import com.team.pj.donghang.domain.entity.CustomUserDetails;
import com.team.pj.donghang.domain.entity.PlaceCommon;
import com.team.pj.donghang.repository.PlaceCommonRepository;
import com.team.pj.donghang.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/place")
public class PlaceController {

    private final String CONTENT_TYPE_ID_RESTAURANT = "39";

    @Autowired
    PlaceCommonRepository placeCommonRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/recommend")
    @ApiOperation(value = "현재 화면 상에서 추천 여행지 목록 조회 (현재는 설문조사 필터링 없이 화면 내의 모든 여행지 정보 조회)")
    public ResponseEntity<?> getRecommendPlaces(
            // @ApiIgnore Authentication authentication,
            @RequestParam String mapx1,
            @RequestParam String mapy1,
            @RequestParam String mapx2,
            @RequestParam String mapy2
    ) {
        // 설문조사 결과 조회를 위해 user 정보 필요.
        // CustomUserDetails userDetails = (CustomUserDetails) authentication.getDetails();

        // (mapx1, mapy1) ~ (mapx2, mapy2) 사이의 content_type_id가 39가 아닌 (음식점이 아닌) 여행지를 모두 찾는다
        List<PlaceCommon> allPlaces = placeCommonRepository.findPlaceCommonByMapxBetweenAndMapyBetweenAndContentTypeIdIsNot(mapx1, mapx2, mapy1, mapy2, CONTENT_TYPE_ID_RESTAURANT);
        Collections.shuffle(allPlaces);

        if(allPlaces.size() <= 20) {
            return ResponseEntity.status(HttpStatus.OK).body(allPlaces);
        } else {
            List<PlaceCommon> results = new ArrayList<>();
            for(int i=0; i<20; i++) {
                results.add(allPlaces.get(i));
            }
            return ResponseEntity.status(HttpStatus.OK).body(results);
        }
    }

    @GetMapping("/restaurants")
    @ApiOperation(value = "현재 화면 상의 음식점 조회")
    public ResponseEntity<?> getRestaurants(
            @RequestParam String mapx1,
            @RequestParam String mapy1,
            @RequestParam String mapx2,
            @RequestParam String mapy2
    ) {
        // (mapx1, mapy1) ~ (mapx2, mapy2) 사이의 content_type_id가 39인 음식점을 모두 찾는다
        List<PlaceCommon> results = placeCommonRepository.findPlaceCommonByMapxBetweenAndMapyBetweenAndContentTypeIdIs(mapx1, mapx2, mapy1, mapy2, CONTENT_TYPE_ID_RESTAURANT);

        return ResponseEntity.status(HttpStatus.OK).body(results);

    }
}
