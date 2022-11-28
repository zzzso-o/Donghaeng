package com.team.pj.donghang.service;

import com.team.pj.donghang.domain.dto.SurveyUrlDto;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private int length =20;
    private String baseUrl = "http://j7a504.p.ssafy.io/survey/key=?";
//user num..
    @Override
    public String generateUrl(User user) {
        StringBuilder builder ;
        String key,generate;

        while (true) {
            builder= new StringBuilder();
            generate = RandomStringUtils.randomAlphabetic(length);
            key =builder.append(baseUrl).append(generate).toString();
            if(!isUrlExist(key)){
                break;
            }
        }
        SurveyUrlDto surveyUrlDto =SurveyUrlDto.builder()
                .user(user)
                .id(key)
                .build();

        urlRepository.save(surveyUrlDto);

        return key;
    }

    @Override
    public boolean isUrlExist(String url) {
        boolean result = urlRepository.existsById(url);
        return result;
    }

    @Override
    public void urlDelete(String url) {
        urlRepository.deleteById(url);
    }

    @Override
    public User getUrlUser(String url) {
        SurveyUrlDto surveyUrlDto = urlRepository.findById(url).orElseGet(SurveyUrlDto::new);;
        if(surveyUrlDto.getUser()!=null){
            return surveyUrlDto.getUser();
        }else {
            return null;
        }
    }
}
