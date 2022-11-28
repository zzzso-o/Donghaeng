package com.team.pj.donghang.api.request;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripMissionCreateDto {
    private String content;
    private Long tripNo;
}
