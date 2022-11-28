import React from "react";
import SpotDetail from "./spotdetail";
import {
  Dialog,
  Card,
  CardActions,
  CardContent,
  Button,
  Typography,
  CardMedia,
  styled,
  Box,
} from "@mui/material";
import ClearIcon from "@mui/icons-material/Clear";
import { fontFamily, typography } from "@mui/system";
//코스관련 사이드바 내부 컨텐츠

const SideContents = (props) => {
  return (
    <SpotCard
      elevation={3}
      onClick={() => {
        props.setSelectedSpot(props.spot);
      }}
    >
      {props.spot.firstImage1 === "" ? (
        <CardMedia
          component="img"
          image="../img/kyeongju.jpg"
          alt="image not found"
          style={{
            width: "40%",
            margin: "3%",
            display: "flex",
            justifyContent: "center",
          }}
        />
      ) : (
        <CardMedia
          component="img"
          image={props.spot.firstImage1}
          alt="image not found"
          style={{
            width: "40%",
            margin: "3%",
            display: "flex",
            justifyContent: "center",
            objectFit: "fill",
          }}
        />
      )}
      <CardContent style={{ width: "45%" }}>
        <IndexBox>
          <IndexText>{props.spotIndex + 1}번 여행지</IndexText>
          <DeleteBtn
            onClick={() => {
              props.deleteCourse(props.spotIndex);
            }}
            color="red"
          />
        </IndexBox>
        <SpotName>{props.spot.title}</SpotName>
        <Address>{props.spot.addr1} </Address>
        <Address>{props.spot.addr2}</Address>
      </CardContent>
    </SpotCard>
  );
};
export default SideContents;

const SpotCard = styled(Card)({
  minWidth: 275,
  marginBottom: "1rem",
  display: "inline-flex",
  height: "15vh",
  width: "100%",
});

const IndexBox = styled(Box)({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "end",
  marginBottom: 2,
  marginTop: 2,
});

const IndexText = styled(Typography)({
  fontSize: 12,
  fontFamily: "HallymGothic-Regular",
  color: "#1976D2",
});

const DeleteBtn = styled(ClearIcon)({
  color: "red",
  fontSize: 18,
});

const SpotName = styled(Typography)({
  fontWeight: "bold",
  fontFamily: "HallymGothic-Regular",
  textAlign: "left",
  textOverflow: "ellipsis",
  overflow: "hidden",
  whiteSpace: "nowrap",
  width: "100%",
});

const Address = styled(Typography)({
  fontSize: 13,
  color: "grey",
  fontFamily: "HallymGothic-Regular",
  textOverflow: "ellipsis",
  overflow: "hidden",
  whiteSpace: "nowrap",
  textAlign: "left",
});
