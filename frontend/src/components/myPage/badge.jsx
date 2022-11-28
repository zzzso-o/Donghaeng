import React, { useState } from "react"
import {
  Box,
  Button,
  Paper,
  styled,
  Typography,
  Grid,
  Tooltip,
  Zoom,
} from "@mui/material"
import { LoremIpsum } from "react-lorem-ipsum"

function FormRow() {
  return (
    <React.Fragment>
      <Grid item xs={2.4}>
        <Tooltip
          TransitionComponent={Zoom}
          title={
            <LoremIpsum avgWordsPerSentence={4} avgSentencesPerParagraph={4} />
          }
        >
          <Item>🥇</Item>
        </Tooltip>
      </Grid>
      <Grid item xs={2.4}>
        <Tooltip
          TransitionComponent={Zoom}
          title={
            <LoremIpsum avgWordsPerSentence={4} avgSentencesPerParagraph={4} />
          }
        >
          <Item>🥈</Item>
        </Tooltip>
      </Grid>
      <Grid item xs={2.4}>
        <Tooltip
          TransitionComponent={Zoom}
          title={
            <LoremIpsum avgWordsPerSentence={4} avgSentencesPerParagraph={4} />
          }
        >
          <Item>🥉</Item>
        </Tooltip>
      </Grid>
      <Grid item xs={2.4}>
        <Tooltip TransitionComponent={Zoom} title={<LoremIpsum />}>
          <Item>🏅</Item>
        </Tooltip>
      </Grid>
      <Grid item xs={2.4}>
        <Tooltip
          TransitionComponent={Zoom}
          title={
            <LoremIpsum avgWordsPerSentence={4} avgSentencesPerParagraph={4} />
          }
        >
          <Item>🏆</Item>
        </Tooltip>
      </Grid>
    </React.Fragment>
  )
}

const Badge = () => {
  return (
    <BadgeContainer>
      <BadgeTitle>뱃지</BadgeTitle>
      <Badges sx={{ flexGrow: 1 }}>
        <Grid container spacing={1}>
          <Grid container>
            <FormRow />
          </Grid>
          <Grid container>
            <FormRow />
          </Grid>
          <Grid container>
            <FormRow />
          </Grid>
          <Grid container>
            <FormRow />
          </Grid>
          <Grid container>
            <FormRow />
          </Grid>
        </Grid>
      </Badges>
    </BadgeContainer>
  )
}

export default Badge

const BadgeContainer = styled(Paper)({
  borderRadius: 20,
  width: "53vw",
  height: "95vh",
  backgroundColor: "beige",
  marginLeft: "1.5rem",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignContent: "space-evenly",
})

const BadgeTitle = styled(Typography)({
  fontSize: 30,
  fontWeight: "bold",
  color: "brown",
  textAlign: "center",
  margin: "2rem",
})

const Badges = styled(Box)({
  marginLeft: "4rem",
  marginRight: "4rem",
})

const Item = styled(Paper)({
  background: "linear-gradient(135deg, white, gold, lightyellow)",
  borderRadius: 15,
  textAlign: "center",
  fontSize: 50,
  margin: "1rem",
  paddingTop: "7px",
  paddingBottom: "7px",
})
