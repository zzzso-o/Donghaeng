package com.team.pj.donghang.service;

import com.team.pj.donghang.api.request.SurveyRequestDto;
import com.team.pj.donghang.api.response.SurveyResponseDto;
import com.team.pj.donghang.domain.entity.Survey;
import com.team.pj.donghang.domain.entity.User;
import com.team.pj.donghang.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service("survey service")
public class SurveyServiceImpl implements SurveyService{
    @Autowired
    SurveyRepository surveyRepository;

    @Transactional
    @Override
    public void surveyCreateAUpdate(User user, SurveyRequestDto survey) {
        Long num;
        if(surveyRepository.findByUser(user)!=null){
            Survey survey1 = surveyRepository.findByUser(user);
//            survey1.setId(user.getUserNo());
            survey1.setUser(user);
            survey1.setSurvey1(survey.getSurvey_1());
            survey1.setSurvey2(survey.getSurvey_2());
            survey1.setSurvey3(survey.getSurvey_3());
            survey1.setSurvey4(survey.getSurvey_4());
            survey1.setSurvey5(survey.getSurvey_5());
            survey1.setSurvey6(survey.getSurvey_6());
            survey1.setSurvey7(survey.getSurvey_7());
            survey1.setSurvey8(survey.getSurvey_8());
            num = surveyRepository.save(survey1).getId();
        }else{
            Survey survey1 = Survey.builder()
//                    .user(user)
                    .id(user.getUserNo())
                    .user(user)
                    .survey1(survey.getSurvey_1())
                    .survey2(survey.getSurvey_2())
                    .survey3(survey.getSurvey_3())
                    .survey4(survey.getSurvey_4())
                    .survey5(survey.getSurvey_5())
                    .survey6(survey.getSurvey_6())
                    .survey7(survey.getSurvey_7())
                    .survey8(survey.getSurvey_8())
                    .build();
            num = surveyRepository.save(survey1).getId();

        }
        System.out.println(num);


    }
    @Override
    public SurveyResponseDto getSurveyResult(Long userNo) {
        Survey survey=surveyRepository.findByUser_UserNo(userNo);

        SurveyResponseDto responseDto = SurveyResponseDto.builder()
                .survey_1(survey.getSurvey1())
                .survey_2(survey.getSurvey2())
                .survey_3(survey.getSurvey3())
                .survey_4(survey.getSurvey4())
                .survey_5(survey.getSurvey5())
                .survey_6(survey.getSurvey6())
                .survey_7(survey.getSurvey7())
                .survey_8(survey.getSurvey8())
                .build();
        return responseDto;
    }
}
