//여행 일정 상세 페이지
import { Box } from "@material-ui/core"
import Map from "../components/map"
import React from "react"
import Navbar from "../components/navbar"
import CourseSide from "../components/coursePage/courseside"
import { styled } from "@mui/material"

const CourseDetailPage = () => {
  return (
    <Box>
      <Navbar></Navbar>
      <Box>
        <MapWrapper id="map">
          <Map></Map>
        </MapWrapper>
        <CourseSide></CourseSide>
      </Box>
    </Box>
  )
}
export default CourseDetailPage

const MapWrapper = styled(Box)({
  width: "100%",
  height: "100%",
  position: "absolute",
  zIndex: -1,
})
