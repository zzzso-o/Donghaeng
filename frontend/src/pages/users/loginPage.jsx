import React, { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import axios from "axios"

import {
  Box,
  Button,
  Paper,
  styled,
  TextField,
  Typography,
  InputAdornment,
  FormControl,
  FormHelperText,
  Input,
} from "@mui/material"
import RecommTrip from "../../components/main/recommTrip"
import AccountCircle from "@mui/icons-material/AccountCircle"
import LockOutlinedIcon from "@mui/icons-material/LockOutlined"
import Swal from "sweetalert2"

const LoginPage = () => {
  const BE_URL = process.env.REACT_APP_BACKEND_URL

  const [values, setValues] = useState({
    id: "string",
    password: "",
    showPassword: false,
  })

  const navigate = useNavigate()

  // 유효성 검사
  const [isId, setIsId] = useState(false)
  const [isPassword, setIsPassword] = useState(false)
  const [isEmpty, setIsEmpty] = useState(true)
  // const router = useRouter();

  //에러메시지 저장
  const [idError, setIdError] = useState("아이디를 입력해 주세요")
  const [passwordError, setPasswordError] = useState("비밀번호를 입력해 주세요")

  // 로그인 버튼 클릭
  const onLogin = () => {
    axios
      .post("http://j7a504.p.ssafy.io:8080/auth", {
        id: values.id,
        password: values.password,
      })
      .then((res) => {
        if (res.data) {
          localStorage.setItem("access-token", res.data)
        }
        Swal.fire({
          icon: "success",
          title: "로그인 되었습니다. :)",
          showConfirmButton: false,
          timer: 1500,
        })

        navigate("/")
      })
      .catch((error) => {
        if (error.response.status === 412) {
          Swal.fire({
            icon: "question",
            title: "이메일 인증 후 이용해주세요",
          })
        } else {
          Swal.fire({
            icon: "error",
            title: "아이디나 비밀번호를 다시 확인해 주세요",
            showConfirmButton: false,
            timer: 1500,
          })
        }
      })
  }

  // 이메일 텍스트 필드가 바뀔때 마다 동작
  const handleChangeId = (event) => {
    setValues({
      ...values,
      id: event.target.value,
    })

    if (event.target.value === "") {
      setIdError("아이디를 입력해 주세요")
      setIsId(false)
      setIsId(true)
    } else {
      setIdError("")
      setIsId(true)
      setIsId(false)
    }
  }

  // 패스워드 텍스트 필드가 바뀔때 마다 동작
  const handleChangePassword = (event) => {
    setValues({
      ...values,
      password: event.target.value,
    })

    if (event.target.value === "") {
      setPasswordError("비밀번호를 입력해 주세요.")
      setIsPassword(false)
      setIsEmpty(true)
    } else {
      setPasswordError("")
      setIsPassword(true)
      setIsEmpty(false)
    }
  }

  // 패스워드 보기 버튼이 눌릴때마다 동작
  const handleClickShowPassword = () => {
    setValues({
      ...values,
      showPassword: !values.showPassword,
    })
  }

  const handleMouseDownPassword = (event) => {
    event.preventDefault()
  }

  return (
    <Background>
      <RecommTrip></RecommTrip>
      <Intro>
        동행에 오신 것을
        <br /> 환영합니다
      </Intro>
      <LoginBox elevation={24}>
        <Title>
          <h1>동행</h1>
          <Typography>동행에 오신 것을 환영합니다</Typography>
        </Title>
        <LoginInput>
          <IdInput>
            <Typography>아이디</Typography>
            <Id
              placeholder="아이디를 입력하세요"
              onChange={handleChangeId}
              required
              autoFocus="true"
              variant="outlined"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <AccountCircle />
                  </InputAdornment>
                ),
              }}
            />
            <FormHelperText>{idError}</FormHelperText>
          </IdInput>
          <IdInput>
            <Typography>비밀번호</Typography>
            <Password
              variant="outlined"
              type={values.showPassword ? "text" : "password"}
              value={values.password}
              placeholder="비밀번호를 입력하세요"
              onChange={handleChangePassword}
              required
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <LockOutlinedIcon />
                  </InputAdornment>
                ),
              }}
            />
            <FormHelperText>{passwordError}</FormHelperText>
          </IdInput>
          <LoginButton variant="contained" onClick={onLogin}>
            로그인
          </LoginButton>
        </LoginInput>
      </LoginBox>
    </Background>
  )
}

export default LoginPage

const Background = styled(Box)({
  // backgroundColor: "#c19a6b",
  display: "flex",
  // justifyContent: "space-around",
  alignItems: "center",
  width: "100vw",
  height: "100vh",
})

const LoginBox = styled(Box)({
  // borderRadius: 12,
  display: "flex",
  justifyContent: "space-evenly",
  alignItems: "center",
  // backgroundColor: "white",
  flexDirection: "column",
  width: "40vw",
  height: "60vh",
  marginLeft: "7rem",
})

const Title = styled(Typography)({
  textAlign: "center",
})

const LoginInput = styled(Box)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "space-evenly",
  // height: "60vh",
  width: "20vw",
})

const IdInput = styled(Box)({
  marginTop: "2rem",
  width: "100%",
  display: "flex",
  flexDirection: "column",
  alignItems: "space-evenly",
})

const Id = styled(TextField)({})

const Password = styled(TextField)({})

const Intro = styled(Typography)({
  position: "absolute",
  color: "white",
  fontSize: 40,
  left: "8%",
  top: "45%",
  textAlign: "center",
  fontWeight: "bold",
})

const LoginButton = styled(Button)({
  color: "white",
  fontWeight: "bold",
  backgroundColor: "#c19a6b",
  "&:hover": {
    backgroundColor: "#ba8759",
  },
  marginTop: "2rem",
  fontSize: 17,
})
