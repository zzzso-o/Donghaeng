import React from "react"
import Card from "@mui/material/Card"
import CardHeader from "@mui/material/CardHeader"
import CardMedia from "@mui/material/CardMedia"
import CardContent from "@mui/material/CardContent"
import Typography from "@mui/material/Typography"

//장소 상세 모달
const SpotDetail = (props) => {
  return (
    <Card
      style={{
        width: "50%",
        height: "70%",
        overflow: "auto",
        position: "fixed",
        top: "50%",
        left: "50%",
        transform: `translate(-50%, -50%)`,
      }}
    >
      <CardHeader
        title={props.spot.title}
        subheader={props.spot.addr1 + " " + props.spot.addr2}
      />
      {props.spot.firstImage1 === "" ? (
        <CardMedia
          component="img"
          image="../img/kyeongju.jpg"
          alt="image not found"
          height="400"
        />
      ) : (
        <CardMedia
          component="img"
          image={props.spot.firstImage1}
          alt="image not found"
          height="400"
        />
      )}
    </Card>
  )
}
export default SpotDetail
