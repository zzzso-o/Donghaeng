package com.team.pj.donghang.domain.dto;

import com.team.pj.donghang.domain.entity.User;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@RedisHash(value = "url",timeToLive = -1L)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyUrlDto {
    @Id
    private String id;
//    private String url;
    private User user;
}
