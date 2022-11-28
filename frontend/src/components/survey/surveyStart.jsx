import React from "react"
import { Box, styled, Typography, Button } from "@material-ui/core"

// 취향설문 시작페이지

const SurveyStart = () => {
  return (
    <Box>
      <SurveyTypography>당신의 여행 취향을 파악하기 위한</SurveyTypography>
      <SurveyTypography>질문 몇 가지를 드리겠습니다.</SurveyTypography>
      <SurveyTypography>마음에 드는 대답을 선택해주세요.</SurveyTypography>
    </Box>
  )
}
export default SurveyStart

const SurveyTypography = styled(Typography)({
  color: "#322725",
  fontFamily: "MapoFlowerIsland",
  fontSize: "1.5rem",
})
