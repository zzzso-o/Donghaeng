import React, { useEffect, useState } from "react"
import { TabPanel, TabContext, TabList } from "@mui/lab"
import RecommendContents from "./recommendcontents"
import { Box, Paper, styled, Tab, Typography, Button } from "@mui/material"
import StarsIcon from "@mui/icons-material/Stars"
import interceptor from "../../api/interceptor"
import { AlbumTab } from "../myPage/album"

//코스관련 사이드바
const RecommendBar = (props) => {
  const [value, setValue] = useState("1")

  const handleChange = (event, newValue) => {
    setValue(newValue)
  }
  return (
    <WrapRecommendBar>
      <TabContext value={value}>
        <TabBox>
          <TabList
            onChange={handleChange}
            aria-label="lab API tabs example"
            indicatorColor="undefined"
            textColor="inherit"
          >
            <AlbumTab label="여 행 지" value="1" />
            <AlbumTab label="음 식 점" value="2" />
          </TabList>
          <Button
            style={{
              fontSize: 14,
              borderRadius: 30,
              fontFamily: "HallymGothic-Regular",
              backgroundColor: "#003458",
            }}
            variant="contained"
            onClick={() => {
              interceptor({
                url: `/api/place/recommend?mapx1=${props.currentSpot.mapx1}&mapx2=${props.currentSpot.mapx2}&mapy1=${props.currentSpot.mapy1}&mapy2=${props.currentSpot.mapy2}`,
                method: "get",
              })
                .then((res) => {
                  props.setRecommendspot([])
                  for (let i = 0; i < res.data.length; i++) {
                    if (i > 20) break
                    props.setRecommendspot((recommendspot) => [
                      ...recommendspot,
                      res.data[i],
                    ])
                  }
                })
                .catch((err) => {
                  alert(err)
                })
              interceptor({
                url: `/api/place/restaurants?mapx1=${props.currentSpot.mapx1}&mapx2=${props.currentSpot.mapx2}&mapy1=${props.currentSpot.mapy1}&mapy2=${props.currentSpot.mapy2}`,
                method: "get",
              })
                .then((res) => {
                  props.setRestuarants([])
                  for (let i = 0; i < res.data.length; i++) {
                    if (i > 20) break
                    props.setRestuarants((restaurants) => [
                      ...restaurants,
                      res.data[i],
                    ])
                  }
                })
                .catch((err) => {
                  alert(err)
                })
            }}
          >
            <StarsIcon />
            추천 받기
          </Button>
        </TabBox>
        <TabPanel value="1">
          <StyledRecommendSlide>
            {props.recommendspot.map((user, index) => (
              <RecommendContents
                spot={user}
                key={index}
                idx={index}
                addCourseList={props.addCourseList}
                setSelectedSpot={props.setSelectedSpot}
                selectedSpot={props.selectedSpot}
                deleteRecommendSpot={props.deleteRecommendSpot}
              ></RecommendContents>
            ))}
          </StyledRecommendSlide>
        </TabPanel>
        <TabPanel value="2">
          <StyledRecommendSlide>
            {props.restaurants.map((user, index) => (
              <RecommendContents
                spot={props.restaurants[index]}
                key={index}
                addCourseList={props.addCourseList}
                setSelectedSpot={props.setSelectedSpot}
                selectedSpot={props.selectedSpot}
              ></RecommendContents>
            ))}
          </StyledRecommendSlide>
        </TabPanel>
      </TabContext>
    </WrapRecommendBar>
  )
}
export default RecommendBar

const StyledRecommendSlide = styled(Box)({
  whiteSpace: "nowrap",
  overflowX: "scroll",
  overflowY: "hidden",
  display: "flex",
})

const WrapRecommendBar = styled(Paper)({
  width: "100%",
  height: "72%",
  borderRadius: 5,
})

const TabBox = styled(Box)({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  paddingRight: "1rem",
})
