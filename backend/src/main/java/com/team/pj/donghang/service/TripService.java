package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.TripCreateRequestDto;
import com.team.pj.donghang.api.request.TripUpdateRequestDto;
import com.team.pj.donghang.api.response.LastTripResponseDto;
import com.team.pj.donghang.api.response.TripResponseDto;
import com.team.pj.donghang.domain.dto.PlaceCommonDto;
import com.team.pj.donghang.domain.entity.Trip;
import com.team.pj.donghang.domain.entity.User;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

public interface TripService {
    // 일정들에 딸린 사진들 보기

    //사진 업로드

    //사진 삭제
    // 장소 상세 정보들 list 조회
    public List<?> getPlaceDetail(Long commonNoList, String category);

    public void createTrip(User user, TripCreateRequestDto tripCreateRequestDto);
    //일정 생성하기

    //일정 삭제하기
    public boolean deleteTrip(User user, Long tripNo);
    //일정 수정하기
    public boolean updateTrip(User user, TripUpdateRequestDto tripUpdateRequestDto);
    public TripResponseDto getUserTrip (Long userNo,Long tripNo);
    //일정 한개 가져오기
    public Trip getTripInfo(Long tripNo);
    public List<TripResponseDto> getUserTripList(Long userNo);


    TripResponseDto getTodayTrip(Long userNo);

    //일정 검색??? ????
    public List<LastTripResponseDto> getUserLastTripList(Long userNo) throws ParseException;
    public LastTripResponseDto getUserPastOneTrip(Long userNo,Long TripNo);
}
