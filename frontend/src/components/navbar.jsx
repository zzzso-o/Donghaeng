//navbar 틀만
import React, { useState, useEffect } from "react"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { Box, styled, Typography } from "@mui/material"
import "../App.css"
import Swal from "sweetalert2"

const Navbar = () => {
  const [isLogin, setIslogin] = useState(false)
  const [isExpanded, setIsExpanded] = useState(false)

  const location = useLocation()
  const navigate = useNavigate()

  const handleMenuClick = () => {
    setIsExpanded(!isExpanded)
    console.log(isExpanded)
  }

  useEffect(() => {
    //잘못된 접근 제한
    if (isLogin) {
      if (
        window.location.pathname === "/login" ||
        window.location.pathname === "/signup"
      )
        navigate("/")
    }

    if (localStorage.getItem("access-token")) {
      setIslogin(true)
    } else {
      setIslogin(false)
    }
  }, [location])

  return (
    <Container>
      <Navigate>
        {window.location.pathname !== "/mypage" &&
        window.location.pathname !== "/course/create" ? (
          window.location.pathname === "/" ? (
            <LogoWhite to="/">동행</LogoWhite>
          ) : (
            <LogoBlack to="/">동행</LogoBlack>
          )
        ) : (
          <LogoGlow to="/">동행</LogoGlow>
        )}
        {!isLogin ? (
          <Links>
            <LinkBox to="/login">
              <PageLink>로그인</PageLink>
              <LinkEng>login</LinkEng>
            </LinkBox>
            <LinkBox to="/signup">
              <PageLink>회원가입</PageLink>
              <LinkEng>signup</LinkEng>
            </LinkBox>
          </Links>
        ) : (
          <Links>
            <LinkBox to="/course/create">
              <PageLink>여행하기</PageLink>
              <LinkEng>course</LinkEng>
            </LinkBox>
            <LinkBox to="/mypage">
              <PageLink>마이페이지</PageLink>
              <LinkEng>my page</LinkEng>
            </LinkBox>
            <LinkBox to="/logout">
              <PageLink>로그아웃</PageLink>
              <LinkEng>logout</LinkEng>
            </LinkBox>
          </Links>
        )}
      </Navigate>
    </Container>
  )
}

export default Navbar

const Container = styled(Box)({
  position: "fixed",
  width: "100vw",
  height: "8vh",
  zIndex: 100,
})

const Navigate = styled(Box)({
  // fontFamily: "HallymGothic-Regular",

  display: "flex",
  justifyContent: "space-between",
  alignItems: "center",
})

const LogoBlack = styled(Link)({
  textDecoration: "none",
  fontFamily: "Makgeolli",
  fontSize: 60,
  margin: "2rem",
  marginLeft: "3rem",
  color: "black",
})

const LogoWhite = styled(Link)({
  textDecoration: "none",
  fontFamily: "Makgeolli",
  fontSize: 60,
  margin: "2rem",
  marginLeft: "3rem",
  color: "white",
})

const LogoGlow = styled(Link)({
  textDecoration: "none",
  fontFamily: "Makgeolli",
  fontSize: 60,
  margin: "2rem",
  marginLeft: "3rem",
  color: "white",
  textShadow: "#fc0 1px 0 10px",
})

const Links = styled(Box)({
  marginRight: "3rem",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
})

const LinkBox = styled(Link)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "right",
  alignItems: "end",
  margin: "2rem",
  textShadow: "1px 1px 3px #000000",
  textDecoration: "none",
})

const PageLink = styled(Typography)({
  color: "white",
  fontFamily: "JSongMyung-Regular-KO",
  fontSize: 17,
  "&:hover": {
    transition: "all 0.1s linear",
    opacity: "1",
    fontSize: 18,
  },
})

const LinkEng = styled(Typography)({
  fontFamily: "JSongMyung-Regular-KO",
  fontSize: 14,
  color: "white",
})
