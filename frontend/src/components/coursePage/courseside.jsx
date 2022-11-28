import React from "react"
import SideContents from "./sidecontents"
import { styled, Button, Paper, Box } from "@mui/material"
import Swal from "sweetalert2"
import interceptor from "../../api/interceptor"
import TextField from "@mui/material/TextField"
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs"
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider"
import { DatePicker } from "@mui/x-date-pickers/DatePicker"
import { useNavigate } from "react-router-dom"

// 사이드바

const CourseSide = (props) => {
  const navigate = useNavigate()
  const [startDate, setStartDate] = React.useState(null)
  const [endDate, setEndDate] = React.useState(null)
  const parseDate = (newValue) => {
    if (newValue == null) return "0"
    let temp = newValue.$d.getFullYear().toString()
    if (newValue.$d.getMonth() < 9) {
      temp = temp + "0" + (newValue.$d.getMonth() + 1).toString()
    } else {
      temp = temp + (newValue.$d.getMonth() + 1).toString()
    }
    if (newValue.$d.getDate() < 10) {
      temp = temp + "0" + newValue.$d.getDate().toString()
    } else {
      temp = temp + newValue.$d.getDate().toString()
    }
    return temp
  }

  return (
    <SideBox>
      <MyBox>
        <Date dateAdapter={AdapterDayjs}>
          <DatePicker
            Fullwidth
            label="출발 날짜"
            value={startDate}
            onChange={(newValue) => {
              setStartDate(parseDate(newValue))
              console.log(startDate)
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                fullWidth
                style={{ marginBottom: "15px" }}
              />
            )}
          />
        </Date>
        <Date dateAdapter={AdapterDayjs}>
          <DatePicker
            label="마지막 날짜"
            value={endDate}
            onChange={(newValue) => {
              setEndDate(parseDate(newValue))
              console.log(endDate)
            }}
            renderInput={(params) => (
              <TextField
                {...params}
                fullWidth
                style={{ marginBottom: "15px" }}
              />
            )}
          />
        </Date>
        <StyledCourseSide>
          {props.recommendspot.map((placeList, index) => (
            <SideContents
              key={index}
              spotIndex={index}
              spot={props.recommendspot[index]}
              deleteCourse={props.deleteCourse}
              setSelectedSpot={props.setSelectedSpot}
            ></SideContents>
          ))}
        </StyledCourseSide>
      </MyBox>
      <CourseBtn
        variant="contained"
        onClick={() => {
          Swal.fire({
            title: "일정의 이름을 적어주세요",
            input: "text",
            inputAttributes: {
              autocapitalize: "off",
            },
            showCancelButton: true,
            confirmButtonText: "일정생성",
            showLoaderOnConfirm: true,
            preConfirm: (login) => {
              const commonNoList = []
              for (let i = 0; i < props.recommendspot.length; i++) {
                commonNoList.push(props.recommendspot[i].commonNo)
              }
              interceptor({
                url: "/api/trip",
                method: "post",
                data: {
                  commonNoList: commonNoList,
                  endDate: endDate,
                  startDate: startDate,
                  tripName: login,
                },
              })
                .then((res) => {
                  console.log("createsuccess")
                  Swal.fire({
                    title: "일정이 생성되었습니다!",
                    text: "일정이 성공적으로 생성되었습니다!",
                    icon: "success",
                    timer: 1500,
                    confirmButtonText: "확인",
                  })
                  navigate("/")
                })
                .catch((err) => {
                  alert(err)
                })
            },
          })
        }}
      >
        일정생성
      </CourseBtn>
    </SideBox>
  )
}
export default CourseSide

const StyledCourseSide = styled(Box)({
  width: "100%",
  height: "51vh",
  overflowY: "auto",
  backgroundColor: "white",
  borderRadius: 5,
})

const MyBox = styled(Box)({
  padding: "1rem",
})

const SideBox = styled(Paper)({
  height: "95%",
  width: "20vw",
  borderRadius: 5,
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-between",
  alignitems: "center",
})

const CourseBtn = styled(Button)({
  fontSize: 14,
  borderStartEndRadius: 0,
  borderStartStartRadius: 0,
  color: "white",
  backgroundColor: "#003458",
  fontFamily: "HallymGothic-Regular",
  padding: 15,
})

const Date = styled(LocalizationProvider)({
  margin: "1rem",
})
