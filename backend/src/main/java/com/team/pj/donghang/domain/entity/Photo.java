package com.team.pj.donghang.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name="photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_no")
    private long photoNo;

//    @NotNull
    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne
    @JoinColumn(name = "trip_no")
    private Trip trip;

    //동이라한 이름을 가진 파일이 업로드가 된다면 오류가 생기기에 이를 해결하기 위해서
    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_name")
    private String fileName;
    @Column(name="original_name")
    private String originalName;


}
