package com.team.pj.donghang.repository;

import com.team.pj.donghang.domain.entity.TripPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TripPlaceRepository extends JpaRepository<TripPlace,Long> {
    List<TripPlace> findAllByTrip_TripNo(Long trip_no);
    void deleteByTrip_TripNo(Long trip_no);
}
//findByTeam_TeamIdx