package com.team.pj.donghang.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
;
import lombok.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name="trip")

public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="trip_no")
    private Long tripNo;

//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDD");

//    @Column(name = "start_date")
//    private SimpleDateFormat startDate;


    @Column(name = "start_date")
    private String startDate;
//
//    @Column(name = "end_date")
//    private SimpleDateFormat endDate;
    @Column(name = "end_date")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @NotNull
    @Column(name = "trip_name", length = 50)
    private String tripName;

}
