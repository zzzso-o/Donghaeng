package com.team.pj.donghang.domain.entity;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

/**
 *
 *
 *
 *
 *
 * */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlaceCommon{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="common_no")
    private Long commonNo;

    @Column(name="content_id")
    private String contentId;

    @Column(name="content_type_id")
    private String contentTypeId;

    private String tel;

    private String title;

    @Column(name="first_image1")
    private String firstImage1;

    @Column(name="first_image2")
    private String firstImage2;

    private String cat1;
    private String cat2;
    private String cat3;
    private String addr1;
    private String addr2;
    private String mapx;
    private String mapy;
    private String mlevel;

    @Column(name = "area_code")
    private String areacode;

    @Column(name = "sigungu_code")
    private String sigunguCode;

}
