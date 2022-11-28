package com.team.pj.donghang.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name="trip_mission")
public class TripMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_mission_no")
    private Long tripMissionNo;

    @ManyToOne
    @JoinColumn(name = "trip_no")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "mission_no")
    private Mission mission;

    @Column(name = "photo_uploaded")
    private String photoUploaded = "false";

}


