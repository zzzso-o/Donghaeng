import * as React from "react"
import { Box, styled } from "@material-ui/core"
import TimelineItem, { timelineItemClasses } from "@mui/lab/TimelineItem"
import TimelineSeparator from "@mui/lab/TimelineSeparator"
import TimelineConnector from "@mui/lab/TimelineConnector"
import TimelineContent from "@mui/lab/TimelineContent"
import TimelineDot from "@mui/lab/TimelineDot"

const NowCourse = (props) => {
  return (
    <CourseTimeline
      sx={{
        [`& .${timelineItemClasses.root}:before`]: {
          flex: 0,
          padding: 0,
        },
      }}
    >
      {props.placeList.map((item, i) => (
        <Box key={i} item={item}>
          {i !== props.placeList.length - 1 ? (
            <TimelineItem>
              <TimelineSeparator>
                <TimelineDot />
                <TimelineConnector />
              </TimelineSeparator>
              <TimelineContent fontFamily="HallymGothic-Regular">
                {item.title}
              </TimelineContent>
            </TimelineItem>
          ) : (
            <TimelineItem>
              <TimelineDot />
              <TimelineContent fontFamily="HallymGothic-Regular">
                {item.title}
              </TimelineContent>
            </TimelineItem>
          )}
        </Box>
      ))}
    </CourseTimeline>
  )
}

export default NowCourse

const CoursePoint = styled(Box)({
  background: "tomato",
  borderRadius: 100,
  width: 20,
  height: 20,
  margin: 10,
})

const CourseTimeline = styled(Box)({
  height: "100%",
  fontFamily: "HallymGothic-Regular",
})
