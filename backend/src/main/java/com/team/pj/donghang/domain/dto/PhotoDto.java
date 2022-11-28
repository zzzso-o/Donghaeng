package com.team.pj.donghang.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhotoDto {
    @JsonProperty(value = "photo_no")
    private long photoNo;

    @JsonProperty(value = "photo_path")
    private String photoPath;

    @JsonProperty(value = "original_file_name")
    private String originalFileName;

    @JsonProperty(value = "file_size")
    private Long fileSize;

}
