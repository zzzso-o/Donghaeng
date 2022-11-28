package com.team.pj.donghang.domain.dto;

import com.team.pj.donghang.domain.entity.Mission;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionDto {
    private Mission mission;
    private String photoUploaded;
}
