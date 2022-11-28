package com.team.pj.donghang.service;

//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//
//import com.team.pj.donghang.domain.dto.TripDto;
//import com.team.pj.donghang.domain.entity.Photo;
//import com.team.pj.donghang.domain.entity.User;
//import com.team.pj.donghang.repository.PhotoRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.UUID;

//@RequiredArgsConstructor
//@Service
//@Transactional
public class S3UploadService {
//    @Value("${cloud.aws.s3.bucket}")
//    private String bucket;
//
//    private final AmazonS3 amazonS3;
//
//    private final AmazonS3Client amazonS3Client;
//
//    private final PhotoRepository photoRepository;
//
//
//    public String upload(User user, TripDto tripDto, MultipartFile multipartFile) throws IOException {
//        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
//
//        ObjectMetadata objMeta = new ObjectMetadata();
//        objMeta.setContentLength(multipartFile.getInputStream().available());
//
//        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
//
//        //원본 파일 이름과 변경된 이름 저장
//        Photo photo = Photo.builder().build();
//
//        photoRepository.save(photo);
//
//        return amazonS3.getUrl(bucket, s3FileName).toString();
//    }
}
