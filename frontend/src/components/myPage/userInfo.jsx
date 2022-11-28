import React, { useLayoutEffect } from "react"
import { useSelector } from "react-redux/es/exports"
import {
  Box,
  Button,
  styled,
  Typography,
  Paper,
  Input,
  Badge,
} from "@mui/material"
import { Photo } from "../../pages/users/myPage"
import SmartphoneIcon from "@mui/icons-material/Smartphone"
import EmailIcon from "@mui/icons-material/Email"
import ChangeCircleIcon from "@mui/icons-material/ChangeCircle"
import "../../App.css"

const Info = () => {
  const user = useSelector((state) => state.user)

  return (
    <InfoContainer>
      <MyInfo>{user.nickname}님, 환영합니다!</MyInfo>
      <User elevation={0}>
        <UserInfo>
          <UserPhoto>
            <Photo src={user.profileImage} alt="profile" />
            <ChangePhoto
              color="info"
              onClick={() => {
                console.log(user)
              }}
            />
          </UserPhoto>
          <UserName>
            <MyName>{user.nickname}</MyName>
            <UserId>{`@${user.id}`}</UserId>
          </UserName>
        </UserInfo>
        <EditBtn>수정</EditBtn>
      </User>
      <User elevation={1}>
        <Phone>
          <SmartphoneIcon />
          <Explain>전화번호</Explain>
          <PhoneNumber>{user.phoneNumber}</PhoneNumber>
        </Phone>
        <EditBtn>수정</EditBtn>
      </User>
      <User elevation={1}>
        <Email>
          <EmailIcon />
          <Explain>이메일</Explain>
          <EmailAddress>{user.email}</EmailAddress>
        </Email>
        <EditBtn>수정</EditBtn>
      </User>
    </InfoContainer>
  )
}

export default Info

const InfoContainer = styled(Paper)({
  borderRadius: 5,
  width: "53vw",
  height: "80vh",
  marginLeft: "1.5rem",
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignContent: "center",
})

const MyInfo = styled(Typography)({
  fontSize: 30,
  color: "dark",
  fontWeight: "bold",
  textAlign: "center",
  marginTop: "1rem",
  fontFamily: "HallymGothic-Regular",
})

const User = styled(Paper)({
  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
  marginLeft: "4rem",
  marginRight: "4rem",
  marginBottom: "1rem",
  borderRadius: 5,
  backgroundColor: "white",
})

const UserInfo = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
})

const UserPhoto = styled(Box)({
  display: "flex",
  justifyContent: "center",
  alignItems: "end",
})

const ChangePhoto = styled(ChangeCircleIcon)({
  position: "absolute",
  paddingBottom: 50,
  paddingLeft: 150,
})

const UserName = styled(Box)({
  display: "flex",
  flexDirection: "column",
  // justifyContent: "center",
  alignItems: "start",
})

const MyName = styled(Typography)({
  color: "#1976D2",
  fontWeight: "bold",
  fontSize: 30,
  fontFamily: "IBMPlexSansKR-Regular",
})

export const UserId = styled(Typography)({
  color: "grey",
  fontFamily: "HallymGothic-Regular",
})

const EditBtn = styled(Button)({
  margin: "2rem",
  color: "grey",
  borderRadius: 30,
  fontFamily: "HallymGothic-Regular",
  border: "solid 1px",
})

const Phone = styled(Box)({
  marginLeft: "2rem",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
})

const Explain = styled(Typography)({
  fontFamily: "HallymGothic-Regular",
  marginLeft: 10,
  fontWeight: "bold",
})

const Email = styled(Box)({
  marginLeft: "2rem",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
})

const PhoneNumber = styled(Typography)({
  marginLeft: "1rem",
  color: "#1976D2",
  fontSize: 19,
  fontWeight: "bold",
  fontFamily: "HallymGothic-Regular",
})

const EmailAddress = styled(Typography)({
  marginLeft: "1rem",
  color: "#1976D2",
  fontWeight: "bold",
  fontSize: 19,
  fontFamily: "IBMPlexSansKR-Regular",
})
