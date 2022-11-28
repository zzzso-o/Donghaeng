// 지난 여행 코스(탭)
import React from "react"
import { useState } from "react"
import {
  Box,
  styled,
  Typography,
  Card,
  CardContent,
  CardMedia,
  CardActionArea,
} from "@mui/material"
import { useSelector } from "react-redux/es/exports"
import Map from "../map"
import { CardImg } from "./lastTrip"

//지도
const RecommendContents = (props) => {
  return (
    <StyledCard
      sx={{ maxWidth: 345 }}
      onClick={() => {
        props.setSelectedSpot({
          title: props.spot.title,
          mapx: props.spot.mapx,
          mapy: props.spot.mapy,
        })
        console.log(props.selectedSpot)
      }}
    >
      <CardActionArea>
        <CardImg>
          {props.spot.firstImage1 === "" ? (
            <CardMedia
              component="img"
              image="../img/kyeongju.jpg"
              alt="image not found"
              style={{
                width: "12vw",
                height: "15vh",
              }}
            />
          ) : (
            <CardMedia
              component="img"
              image={props.spot.firstImage1}
              alt="image not found"
              style={{
                width: "12vw",
                height: "15vh",
              }}
            />
          )}
        </CardImg>
        <CardContent>
          <CardText2 gutterBottom component="div">
            {props.spot.title}
          </CardText2>
        </CardContent>
      </CardActionArea>
    </StyledCard>
  )
}
const LastCourse = (props) => {
  const tripInfo = useSelector((state) => state.lastTrips)
  const [level, setLevel] = useState(3)
  const [currentSpot, setCurrentSpot] = useState({
    mapy1: "37.49817473153595",
    mapy2: "37.51783576502551",
    mapx1: "127.03121197537305",
    mapx2: "127.07049421820354",
  })

  const [selectedSpot, setSelectedSpot] = useState({
    title: tripInfo[props.i].placeList[0].title,
    mapy: tripInfo[props.i].placeList[0].mapy,
    mapx: tripInfo[props.i].placeList[0].mapx,
  })

  return (
    <LastCourseContainer>
      <MapContainer id="map">
        <Map
          recommendspot={tripInfo[props.i].placeList}
          selectedSpot={selectedSpot}
          setCurrentSpot={setCurrentSpot}
          setSelectedSpot={setSelectedSpot}
          level={level}
          setLevel={setLevel}
          courseSpot={[]}
          addCourseList={[]}
        />
      </MapContainer>
      <MyBox>
        <MyTypo>여행했던 곳들</MyTypo>
        <MySpots>
          {tripInfo[props.i].placeList.map((user, index) => (
            <RecommendContents
              spot={user}
              key={index}
              setSelectedSpot={setSelectedSpot}
            ></RecommendContents>
          ))}
        </MySpots>
      </MyBox>
    </LastCourseContainer>
  )
}

export default LastCourse

const LastCourseContainer = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItem: "center",
  width: "50vw",
  height: "57vh",
})

const MapContainer = styled(Box)({
  width: "100%",
  height: "100%",
  top: 0,
  borderRadius: 8,
})

const MyBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItem: "center",
  marginLeft: "2rem",
})

const MyTypo = styled(Box)({
  textAlign: "left",
  fontFamily: "HallymGothic-Regular",
  fontWeight: "bold",
  fontSize: 20,
})

const MySpots = styled(Box)({
  width: "100%",
  height: "100%",
  overflowY: "auto",
  whiteSpace: "nowrap",
})

const StyledCard = styled(Card)({
  width: "13vw",
  marginRight: "1rem",
  marginTop: "1rem",
  marginBotton: "1rem",
})

const CardText2 = styled(Typography)({
  textAlign: "left",
  fontFamily: "IBMPlexSansKR-Regular",
  // fontFamily: "HallymGothic-Regular",
  textOverflow: "ellipsis",
  overflow: "hidden",
  whiteSpace: "nowrap",
  width: "100%",
  fontWeight: "bold",
})
