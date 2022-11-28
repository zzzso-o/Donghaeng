package com.team.pj.donghang.api.controller;

import com.team.pj.donghang.api.request.SurveyRequestDto;
import com.team.pj.donghang.api.response.SurveyResponseDto;
import com.team.pj.donghang.domain.entity.CustomUserDetails;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.service.SurveyService;
import com.team.pj.donghang.service.UrlService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "설문조사와 관련된 API" ,tags = {"survey"})
@RestController
@CrossOrigin("*")
@RequestMapping("/survey")
public class SurveyController {
    private final Logger logger = LoggerFactory.getLogger(SurveyController.class);
    private String baseUrl = "http://j7a504.p.ssafy.io/survey/key=?";
    @Autowired
    UrlService urlService;

    @Autowired
    SurveyService surveyService;

    @GetMapping("/generate/url")
    @ApiOperation(value = "설문조사 url 생성해주고 반환 그리고 redis 에 저장하여 url과 유저 매핑")
    @ApiResponses({
            @ApiResponse(code = 201,message = "설문 조사 url 완료됨")
    })
    public ResponseEntity<String> getGenerateUrl(
            @ApiIgnore Authentication authentication
            ){
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        String url = urlService.generateUrl(userDetails.getUser());
        return  ResponseEntity.status(HttpStatus.CREATED).body(url);
    }



    @PutMapping("")
    @ApiOperation(value = "설문조사 결과를 보냄 이때 url도 같이 보냄")
    @ApiResponses({
            @ApiResponse(code = 200,message = "설문 조사 완려됨"),
            @ApiResponse(code =410 ,message = "설문 url 유효기간 만료됨")

    })
    public ResponseEntity<SurveyResponseDto> updateSurvey(
            @ApiParam(value = "url 뒤의 랜덤 값만..!",example = "sdfdsfD") @RequestParam("url") String token,
            @ApiParam @RequestBody SurveyRequestDto surveyRequestDto
    ){
        String url="";
        if(!token.contains("?")) {
            url = url.concat(baseUrl);
            url = url.concat(token);

        }else {
            url = token;
        }

        User user = urlService.getUrlUser(url);
        if(urlService.isUrlExist(url)) {
            surveyService.surveyCreateAUpdate(user, surveyRequestDto);
            urlService.urlDelete(url);
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.GONE);
        }
    }

    @GetMapping("/surveyResult")
    @ApiOperation(value = "설문조사한 결과를 로그인한 유저의 정보에 맞게 반환")
    @ApiResponses({
            @ApiResponse(code = 201,message = "설문 조사 url 완료됨")
    })
    public ResponseEntity<SurveyResponseDto> getSurveyResult(
            @ApiIgnore Authentication authentication
    ){
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();
        SurveyResponseDto survey = surveyService.getSurveyResult(userDetails.getUser().getUserNo());
        return  ResponseEntity.status(HttpStatus.OK).body(survey);
    }

    @GetMapping("/urlVaild")
    @ApiOperation(value = "설문조사 주소가 유효한지 check")
    @ApiResponses({
            @ApiResponse(code = 200,message = "설문 조사 url 유효함"),
            @ApiResponse(code = 410,message = "설문 조사 url 유효하지 않음")
    })
    public ResponseEntity checkUrlVaild(
            @ApiParam(value = "url 뒤에 있는 random token") String token
    ){

        baseUrl.concat(token);
        boolean flag = urlService.isUrlExist(baseUrl);
        if(flag) {
            return new ResponseEntity(HttpStatus.OK);
        }else {
        return  new ResponseEntity(HttpStatus.GONE);
        }
    }



}
