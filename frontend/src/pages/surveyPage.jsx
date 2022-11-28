import { React, useState } from "react"
import {
  Box,
  Button,
  styled,
  Typography,
  Select,
  MenuItem,
  TextField,
  Divider,
  IconButton,
} from "@material-ui/core"
import SurveyEnd from "../components/survey/surveyEnd"
import SurveyStart from "../components/survey/surveyStart"
import { useDispatch } from "react-redux"
import axios from "axios"
import * as Yup from "yup"
import { useFormik } from "formik"
import interceptor from "../api/interceptor"
import queryString from "query-string"
import { useLocation } from "react-router-dom"
import Fade from "react-reveal/Fade"
import ArrowBackIosIcon from "@mui/icons-material/ArrowBackIos"

// 취향설문 페이지

// 질문리스트
const questionList = [
  "좋았던 국내 여행지는 어디였나요?",
  "선호하는 여행 스타일과 가장 가까운 것을 골라주세요.",
  "유적지나 예술 작품 보러 가실래요?",
  "더 많은 시간을 보내고 싶은 곳을 골라주세요.",
  "가고 싶은 여행지 분위기를 알려주세요",
  "액티비티나 레포츠는 어떤가요?",
  "쇼핑 갈까요?",
  "나이대가 어떻게 되시나요?",
]

// localhost:3000

const SurveyPage = () => {
  const { search } = useLocation()
  const { type } = queryString.parse(search)
  // console.log(search)

  const dispatch = useDispatch()
  const formik = useFormik({
    initialValues: {
      survey_1: "",
      survey_2: "",
      survey_3: "",
      survey_4: "",
      survey_5: "",
      survey_6: "",
      survey_7: "",
      survey_8: "",
    },
    onSubmit: async (data) => {
      console.log(data)
      interceptor({
        url: "/survey?url=" + search.substring(1),
        method: "put",
        data: data,
      })
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          alert(err)
        })
    },
  })

  const [page, setPage] = useState(0)
  const [number, setNumber] = useState(1)
  const [end, setEnd] = useState(false)
  const [start, setStart] = useState(false)

  const nextPage = () => {
    setPage(page + 1)
    setNumber(number + 1)
  }

  const prevPage = () => {
    setPage(page - 1)
    setNumber(number - 1)
  }

  const surveyEnd = () => {
    setEnd(true)
  }

  const surveyStart = () => {
    setStart(true)
  }

  return (
    <Background>
      <SurveyBackground>
        {start === false ? (
          <Fade duration={1500}>
            <SB>
              <SurveyStart></SurveyStart>
              <StartButton onClick={surveyStart} disableElevation disableRipple>
                시작하기!
              </StartButton>
            </SB>
          </Fade>
        ) : (
          <form onSubmit={formik.handleSubmit}>
            <Box>
              {end === false ? (
                <SurveyBox>
                  <TopBox>
                    {page === 0 ? (
                      <Box style={{ height: "5vh" }}></Box>
                    ) : page > 0 && page < 8 ? (
                      <PrevBox>
                        <IconButton onClick={prevPage}>
                          <ArrowBackIosIcon />
                        </IconButton>
                      </PrevBox>
                    ) : null}
                  </TopBox>

                  <QuestionBox>
                    <Box>Q.{number}</Box>
                    <Box style={{ marginTop: "3%" }}>{questionList[page]}</Box>
                  </QuestionBox>

                  <AnswerBox>
                    {page === 0 ? (
                      <ButtonBox>
                        <ContentBox>
                          <TextField
                            type="text"
                            id="answer1"
                            name="survey_1"
                            value={formik.values.survey_1}
                            onChange={formik.handleChange}
                          />
                          <OneButton onClick={nextPage}>완료</OneButton>
                        </ContentBox>
                      </ButtonBox>
                    ) : page === 1 ? (
                      <FourBox>
                        <FourButton
                          type="button"
                          id="answer21"
                          name="survey_2"
                          value={formik.values.survey_2}
                          onClick={() => {
                            formik.setFieldValue("survey_2", "0")
                            nextPage()
                          }}
                        >
                          최대한 많은 관광지를 둘러보는 관광
                        </FourButton>
                        <FourButton
                          id="answer22"
                          type="button"
                          name="survey_2"
                          value={formik.values.survey_2}
                          onClick={() => {
                            formik.setFieldValue("survey_2", "1")
                            nextPage()
                          }}
                        >
                          쉬엄쉬엄 여유롭게 구경하는 스타일
                        </FourButton>
                        <FourButton
                          id="answer23"
                          type="button"
                          name="survey_2"
                          value={formik.values.survey_2}
                          onClick={() => {
                            formik.setFieldValue("survey_2", "2")
                            nextPage()
                          }}
                        >
                          구경보다는 편안한 곳에서 느긋하게 힐링
                        </FourButton>
                        <FourButton
                          id="answer24"
                          type="button"
                          name="survey_2"
                          value={formik.values.survey_2}
                          onClick={() => {
                            formik.setFieldValue("survey_2", "3")
                            nextPage()
                          }}
                        >
                          같이 가는 사람이 하자는 대로 다니는 스타일
                        </FourButton>
                      </FourBox>
                    ) : page === 2 ? (
                      <ButtonBox>
                        <SurveyButton
                          id="answer31"
                          type="button"
                          name="survey_3"
                          value={formik.values.survey_3}
                          onClick={() => {
                            formik.setFieldValue("survey_3", "0")
                            nextPage()
                          }}
                        >
                          좋아요
                        </SurveyButton>
                        <SurveyButton
                          id="answer32"
                          name="survey_3"
                          type="button"
                          value={formik.values.survey_3}
                          onClick={() => {
                            formik.setFieldValue("survey_3", "1")
                            nextPage()
                          }}
                        >
                          싫어요
                        </SurveyButton>
                      </ButtonBox>
                    ) : page === 3 ? (
                      <ButtonBox>
                        <SurveyButton
                          id="answer41"
                          type="button"
                          name="survey_4"
                          value={formik.values.survey_4}
                          onClick={() => {
                            formik.setFieldValue("survey_4", "0")
                            nextPage()
                          }}
                        >
                          물 좋고 공기 좋은 곳
                        </SurveyButton>
                        <SurveyButton
                          id="answer42"
                          name="survey_4"
                          type="button"
                          value={formik.values.survey_4}
                          onClick={() => {
                            formik.setFieldValue("survey_4", "1")
                            nextPage()
                          }}
                        >
                          도회적이고 깔끔한 공간
                        </SurveyButton>
                      </ButtonBox>
                    ) : page === 4 ? (
                      <ButtonBox>
                        <SurveyButton
                          id="answer51"
                          type="button"
                          name="survey_5"
                          value={formik.values.survey_5}
                          onClick={() => {
                            formik.setFieldValue("survey_5", "0")
                            nextPage()
                          }}
                        >
                          활기차고 즐거운 분위기
                        </SurveyButton>
                        <SurveyButton
                          id="answer52"
                          type="button"
                          name="survey_5"
                          value={formik.values.survey_5}
                          onClick={() => {
                            formik.setFieldValue("survey_5", "1")
                            nextPage()
                          }}
                        >
                          잔잔하고 평화로운 분위기
                        </SurveyButton>
                      </ButtonBox>
                    ) : page === 5 ? (
                      <ButtonBox>
                        <SurveyButton
                          id="answer61"
                          type="button"
                          name="survey_6"
                          value={formik.values.survey_6}
                          onClick={() => {
                            formik.setFieldValue("survey_6", "0")
                            nextPage()
                          }}
                        >
                          좋아요
                        </SurveyButton>
                        <SurveyButton
                          id="answer62"
                          type="button"
                          name="survey_6"
                          value={formik.values.survey_6}
                          onClick={() => {
                            formik.setFieldValue("survey_6", "1")
                            nextPage()
                          }}
                        >
                          싫어요
                        </SurveyButton>
                      </ButtonBox>
                    ) : page === 6 ? (
                      <ButtonBox>
                        <SurveyButton
                          id="answer71"
                          name="survey_7"
                          type="button"
                          value={formik.values.survey_7}
                          onClick={() => {
                            formik.setFieldValue("survey_7", "0")
                            nextPage()
                          }}
                        >
                          백화점,아울렛
                        </SurveyButton>
                        <SurveyButton
                          id="answer72"
                          name="survey_7"
                          type="button"
                          value={formik.values.survey_7}
                          onClick={() => {
                            formik.setFieldValue("survey_7", "1")
                            nextPage()
                          }}
                        >
                          시장
                        </SurveyButton>
                        <SurveyButton
                          id="answer73"
                          type="button"
                          name="survey_7"
                          value={formik.values.survey_7}
                          onClick={() => {
                            formik.setFieldValue("survey_7", "2")
                            nextPage()
                          }}
                        >
                          아니요
                        </SurveyButton>
                      </ButtonBox>
                    ) : (
                      <ButtonBox>
                        <Select
                          id="answer8"
                          name="survey_8"
                          value={formik.values.survey_8}
                          onChange={formik.handleChange}
                          label="age"
                          style={{ width: "30%", marginTop: "2rem" }}
                        >
                          <MenuItem value="40">40대</MenuItem>
                          <MenuItem value="50">50대</MenuItem>
                          <MenuItem value="60">60대</MenuItem>
                          <MenuItem value="70">70대</MenuItem>
                          <MenuItem value="80">80대 이상</MenuItem>
                        </Select>
                      </ButtonBox>
                    )}
                  </AnswerBox>
                  <FooterBox>
                    {page === 7 ? (
                      <Box>
                        <CompleteButton type="submit" onClick={surveyEnd}>
                          제출하기
                        </CompleteButton>
                      </Box>
                    ) : null}
                    {/* <Page>
                    <PageBox>{number} / 8</PageBox>
                  </Page> */}
                  </FooterBox>
                </SurveyBox>
              ) : (
                <Fade duration={1500}>
                  <Box
                    style={{
                      width: "20vw",
                      height: "10vh",
                      background: "white",
                      borderRadius: "10px",
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                    }}
                  >
                    <Typography
                      style={{
                        color: "#322725",
                        fontFamily: "MapoFlowerIsland",
                        fontSize: "1.5rem",
                      }}
                    >
                      설문에 참여해주셔서 감사합니다.
                    </Typography>
                  </Box>
                </Fade>
              )}
            </Box>
          </form>
        )}
      </SurveyBackground>
    </Background>
  )
}
export default SurveyPage

const Background = styled(Box)({
  background: "#B59B89",
  display: "flex",
  alignContent: "center",
  justifyContent: "center",
  width: "100wh",
  height: "100vh",
  backgroundImage: "url(" + "../img/water.jpg" + ")",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
  backgroundAttachment: "fixed",
  backgroundPosition: "top center",
})

const SurveyBackground = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  paddingTop: "8vh",
  width: "100%",
  height: "92%",
  backgroundColor: "rgba(0, 0, 0, 0.4)",
  position: "absolute",
})

const SB = styled(Box)({
  display: "flex",
  width: "20vw",
  height: "70vh",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "cetner",
  background: "white",
  borderRadius: "10px",
  padding: "3%",
})

const FourBox = styled(Box)({
  width: "100%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
  marginBottom: "1rem",
})

const FourButton = styled(Button)({
  width: "80%",
  height: "30%",
  fontSize: "1rem",
  borderRadius: "10px",
  fontFamily: "MapoFlowerIsland",
  marginTop: "3%",
  border: "solid 1px",
  color: "#322725",
})

const PageBox = styled(Box)({
  marginBottom: "5%",
  color: "#322725",
})

const Page = styled(Box)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "flex-end",
})

const PrevButton = styled(Button)({
  backgroundColor: "#C99353",
  color: "#FFFFFF",
  fontSize: 16,
  borderRadius: 10,
  fontFamily: "MapoFlowerIsland",
  margin: "1rem",
})

const StartButton = styled(Button)({
  color: "#322725",
  width: "100%",
  fontSize: "2rem",
  marginTop: "3%",
  fontFamily: "MapoFlowerIsland",
  position: "relative",
  "&:hover": {
    textDecoration: "underline 3px",
  },
})

const ContentBox = styled(Box)({
  display: "flex",
  width: "100%",
  flexDirection: "row",
  justifyContent: "center",
})

const OneButton = styled(Button)({
  color: "#322725",
  fontSize: "1.2rem",
  fontFamily: "MapoFlowerIsland",
  marginLeft: "5%",
  fontSize: "1rem",
  borderRadius: "10px",
  border: "solid 1px",
  color: "#322725",
})

// -----------new-------------
const SurveyBox = styled(Box)({
  display: "flex",
  width: "25vw",
  height: "55vh",
  padding: "3%",
  background: "white",
  borderRadius: "10px",
  flexDirection: "column",
  justifyContent: "flex-start",
  // alignItems: "center",
})

const TopBox = styled(Box)({})
const PrevBox = styled(Box)({
  display: "flex",
  justifyContent: "flex-start",
})
const QuestionBox = styled(Box)({
  fontSize: "1.5rem",
  width: "100%",
  color: "#322725",
  fontFamily: "MapoFlowerIsland",
  // marginTop: "5%",
})
const AnswerBox = styled(Box)({
  width: "100%",
  marginTop: "5%",
})
const FooterBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  alignContent: "center",
  textAlign: "center",
})

const ButtonBox = styled(Box)({
  width: "100%",
  height: "40%",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
  marginBottom: "1%",
  marginTop: "5%",
})

const SurveyButton = styled(Button)({
  width: "50%",
  height: "70%",
  fontSize: "1rem",
  borderRadius: "10px",
  fontFamily: "MapoFlowerIsland",
  color: "white",
  marginTop: "5%",
  border: "solid 1px",
  color: "#322725",
})

const CompleteButton = styled(Button)({
  color: "white",
  width: "50%",
  fontSize: "1.5rem",
  marginTop: "3%",
  fontFamily: "MapoFlowerIsland",
  position: "relative",
  background: "#8d6248",
})
