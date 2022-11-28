package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.TripCreateRequestDto;
import com.team.pj.donghang.api.response.TripResponseDto;
import com.team.pj.donghang.domain.entity.User;

import java.util.List;

public interface BaseService <C>{
    public List<? extends C> recommendPlaceList(List<Long> commonNoList);
    public void create(C type);
    //일정 생성하기

    //일정 삭제하기
    public void delete(Long id);
    //일정 수정하기
    public void updateTrip(User user, TripCreateRequestDto tripCreateRequestDto, List<Long> tripPlaceList);
    public TripResponseDto getUserTrip (Long userNo, Long tripNo);
    //일정 한개 가져오기
    public List<TripResponseDto> getUserTripList(Long userNo);
}
