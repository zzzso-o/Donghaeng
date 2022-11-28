package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.entity.Trip;
import com.team.pj.donghang.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    /***여행기록 사진 업로드 userSchedule은 수정필요
     * uploadTripPhoto
     *
     * @param user
     * @param tripDto
     * @param multipartFile
     * @return : 이미지 경로
     */
    public String uploadTripPhoto(User user, Trip tripDto, MultipartFile multipartFile);

    String createProfileImageName(String UserName);

    String getFileExtension(String fileName);

    String getFilePath(String newFileName);

    void deletePhoto(Trip trip);

    String updateProfileImg(User user, MultipartFile multipartFile);

    String createProfileImage(User user, MultipartFile multipartFile);

    List<String> getImageUrlList(Trip trip);
}
