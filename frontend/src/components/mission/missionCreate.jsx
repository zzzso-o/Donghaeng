import * as React from "react"
import { Typography, Box, Button, styled } from "@material-ui/core"
import axios from "axios"
import interceptor from "../../api/interceptor"
import { useState } from "react"
import { setMission } from "../../features/mission/missionSlice"
import { useDispatch, useSelector } from "react-redux"
import Swal from "sweetalert2"

const CreateMission = (props) => {
  const [content, setContent] = useState("")
  const mission = useSelector((state) => state.mission)
  const dispatch = useDispatch()

  const close = () => {
    props.setOpen(false)
  }
  const onChangeContent = (e) => {
    setContent(e.target.value)
  }
  console.log(props.close)
  const CreateMissionRequest = (url, method, data) => {
    interceptor({
      url: "/api/mission",
      method: "post",
      data: {
        content: content,
        tripNo: props.tripNo,
      },
    })
      .then((res) => {
        Swal.fire({
          icon: "success",
          title: "미션 생성 성공! :)",
          showConfirmButton: false,
          timer: 1500,
        })
        close()
        axios
          .get(
            "http://j7a504.p.ssafy.io:8080/api/mission/trip?tripNo=" +
              props.tripNo
          )
          .then((res) => {
            console.log(res.data)
            dispatch(setMission(res.data))
          })
      })
      .catch((err) => {
        alert(err)
      })
  }

  return (
    <ModalBox>
      <Typography
        id="modal-modal-title"
        variant="h6"
        component="h2"
        fontFamily="HallymGothic-Regular"
        marginBottom="3%"
      >
        미션 만들기
      </Typography>
      <Typography id="modal-modal-description" style={{}}>
        <input
          type="text"
          onChange={onChangeContent}
          value={content}
          style={{
            width: "20vw",
            height: "10vh",
            padding: "1%",
            fontFamily: "HallymGothic-Regular",
            borderRadius: "10px",
            border: "none",
            marginTop: "3%",
            marginBottom: "5%",
          }}
        />
      </Typography>
      <Box>
        <Button
          onClick={() => CreateMissionRequest()}
          style={{ fontSize: "1rem", background: "white" }}
        >
          등록
        </Button>
      </Box>
    </ModalBox>
  )
}

export default CreateMission

const ModalBox = styled(Box)({
  width: "100%",
  height: "100%",
  background: "#BDCFDD",
  display: "flex",
  alignItems: "center",
  flexDirection: "column",
  padding: "3%",
})
