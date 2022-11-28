package com.team.pj.donghang.domain.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TripPlace  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_place_no")
    private Long tripPlaceNo;

    @ManyToOne
    @JoinColumn(name = "common_no")
    private PlaceCommon common;
    @ManyToOne
    @JoinColumn(name = "trip_no")
    private Trip trip;
}
