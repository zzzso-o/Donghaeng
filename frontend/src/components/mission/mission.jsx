import { React, useState, useEffect, useRef } from "react"
import { Box, styled, Grid, Paper, Button, IconButton } from "@material-ui/core"
import Carousel from "react-material-ui-carousel"
import CameraAltIcon from "@mui/icons-material/CameraAlt"
import RefreshIcon from "@mui/icons-material/Refresh"
import axios from "axios"
import interceptor from "../../api/interceptor"
import Typography from "@mui/material/Typography"
import Modal from "@mui/material/Modal"
import ClearIcon from "@mui/icons-material/Clear"
import { setMission } from "../../features/mission/missionSlice"
import { useDispatch, useSelector } from "react-redux"
import Swal from "sweetalert2"
import { Img } from "../../pages/mainPage"

// const MissionGrid = styled(Grid)({
//   display: "flex",
//   flexDirection: "column",
//   alignItems: "center",
// })

// const MissionBox = styled(Box)({
//   background: "yellow",
//   width: "70%",
//   borderRadius: 20,
//   textAlign: "center",
//   margin: 10,
// })

const Mission = (props) => {
  const mission = useSelector((state) => state.mission)
  const dispatch = useDispatch()

  if (props.tripNo !== 0 && mission.length === 0) {
    interceptor({
      url: "/api/mission/trip?tripNo=" + props.tripNo,
      method: "get",
    })
      .then((res) => {
        console.log(res.data)
        dispatch(setMission(res.data))
      })
      .catch((err) => {
        alert(err)
      })
  }

  const [photoOpen, setPhotoOpen] = useState(false)
  const handlePhotoOpen = () => setPhotoOpen(true)

  const handlePhotoClose = () => setPhotoOpen(false)

  const [selectImages, setSelectImages] = useState()

  const [selectedMission, setSelectedMission] = useState()

  const sendMissionNo = (missionNo) => {
    setSelectedMission(missionNo)
    setPhotoOpen(true)
  }

  const onClickImageHandler = () => {
    const url =
      "http://j7a504.p.ssafy.io:8080/upload/trip/?tripNo=" + props.tripNo
    const formData = new FormData()
    formData.append("images", selectImages)

    const config = {
      headers: {
        "content-type": "multipart/form-data",
        Authorization: "Bearer " + localStorage.getItem("access-token"),
      },
    }

    console.log("mission", selectedMission)

    axios.post(url, formData, config).then((res) => {
      interceptor({
        url: `/api/mission/photo?missionNo=${selectedMission}&tripNo=${props.tripNo}`,
        method: "put",
      }).then(() => {
        axios
          .get(
            "http://j7a504.p.ssafy.io:8080/api/mission/trip?tripNo=" +
              props.tripNo
          )
          .then((res) => {
            console.log("missions after upload image", res.data)
            dispatch(setMission(res.data))
          })
        Swal.fire({
          icon: "success",
          title: "미션 성공!",
          showConfirmButton: false,
          timer: 1500,
        })
        handlePhotoClose()
      })
    })
  }
  const fileChangeHandler = (e) => {
    const files = e.target.files[0]
    setSelectImages(files)
  }

  const imgRef = useRef()

  const onClickFileBtn = (e) => {
    imgRef.current.click()
  }

  // reroll 함수
  const rerollMission = (missionNo) => {
    console.log(missionNo, props.tripNo)
    axios
      .get(
        `http://j7a504.p.ssafy.io:8080/api/mission/refresh?missionNo=${missionNo}&tripNo=${props.tripNo}`
      )
      .then((res) => {
        console.log(res)
        axios
          .get(
            "http://j7a504.p.ssafy.io:8080/api/mission/trip?tripNo=" +
              props.tripNo
          )
          .then((res) => {
            dispatch(setMission(res.data))
          })
      })
      .catch((err) => {
        alert(err)
      })
  }

  // delete 함수
  const deleteMission = (missionNo) => {
    axios
      .delete(
        `http://j7a504.p.ssafy.io:8080/api/mission?missionNo=${missionNo}`
      )
      .then((res) => {
        Swal.fire({
          icon: "error",
          title: "미션이 삭제되었습니다.",
          showConfirmButton: false,
          timer: 1500,
        })
        axios
          .get(
            "http://j7a504.p.ssafy.io:8080/api/mission/trip?tripNo=" +
              props.tripNo
          )
          .then((res) => {
            dispatch(setMission(res.data))
          })
      })
      .catch((err) => {
        alert(err)
      })
  }

  return (
    <MissionBox>
      {mission.map((item, i) => (
        <MissionPaper
          key={i}
          item={item}
          style={
            item.photoUploaded === "true" ? { background: "gray" } : 
            (
              item.mission.missionCategoryNo === 0 ? 
              { background: "#d5c0b4" } : 
              item.mission.missionCategoryNo === 1 ? 
              { background: "#f4b37b" } : 
              { background: "#BDCFDD" }
            )
          }
        >
          <MissionTypeBox>
            {item.mission.missionCategoryNo === 0 ? (
              <Typography>mission</Typography>
            ) : item.mission.missionCategoryNo === 1 ? (
              <Typography>special mission</Typography>
            ) : (
              <Typography>custom mission</Typography>
            )}
            {item.mission.missionCategoryNo === 2 ? (
              <IconButton onClick={() => deleteMission(item.mission.missionNo)}>
                <ClearIcon />
              </IconButton>
            ) : item.photoUploaded === "true" ? null : (
              <IconButton onClick={() => rerollMission(item.mission.missionNo)}>
                <RefreshIcon />
              </IconButton>
            )}
          </MissionTypeBox>
          <ContentBox>
            <Box>{item.mission.content}</Box>
            {item.photoUploaded === "true" ? null : (
              <Button
                style={{ fontFamily: "HallymGothic-Regular" }}
                onClick={() => sendMissionNo(item.mission.missionNo)}
                endIcon={<CameraAltIcon />}
              >
                미션 완료
              </Button>
            )}
            <Modal
              open={photoOpen}
              onClose={handlePhotoClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
            >
              <PhotoModalBox>
                <input
                  type="file"
                  ref={imgRef}
                  accept="image/*"
                  onChange={fileChangeHandler}
                  style={{ display: "none" }}
                />
                <Button onClick={() => onClickFileBtn()}>이미지 선택</Button>
                <Button
                  onClick={() => {
                      onClickImageHandler()
                    }
                  }
                >
                  등록
                </Button>
              </PhotoModalBox>
            </Modal>
          </ContentBox>
        </MissionPaper>
      ))}
    </MissionBox>
  )
}

export default Mission

const MissionBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  fontFamily: "HallymGothic-Regular",
  overflow: "auto",
  borderRadius: 10,
  marginLeft: "1rem",
  marginRight: "1rem",
})

const MissionPaper = styled(Paper)({
  display: "flex",
  justifyContent: "center ",
  alignItems: "center",
  justifyContent: "space-between",
  marginBottom: "1rem",
  borderRadius: 15,
  width: "100%",
  // padding: "2%",
})

const MissionTypeBox = styled(Box)({
  width: "15%",
})

const ContentBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  justifyContent: "center",
  fontSize: "1.2em",
  height: "100px",
  width: "80%",
})

const PhotoModalBox = styled(Box)({
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "25vw",
  height: "20vh",
  bgcolor: "background.paper",
  border: "none",
  borderRadius: "10px",
  boxShadow: "24",
  p: "4",
  background: "white",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
})
