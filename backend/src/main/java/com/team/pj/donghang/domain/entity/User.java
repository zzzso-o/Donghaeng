package com.team.pj.donghang.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_no")
    private Long userNo;

    @Column(unique = true, length = 20)
    @NotNull
    private String id;
    private String password;

    @Column(unique = true, length = 20)
    @NotNull
    private String nickname;

    @Column(unique = true, length = 50)
    @NotNull
    private String email;

    @Column(name ="profile_image")
    private String profileImage;

    @Column(name = "phone_number")
    private String phoneNumber;

}
