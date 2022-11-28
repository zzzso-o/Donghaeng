import axios from "axios"
import { toast, ToastContainer } from "react-toastify"
import "react-toastify/dist/ReactToastify.css"
import { Formik, ErrorMessage } from "formik"
import * as Yup from "yup"
import {
  Button,
  TextField,
  Box,
  styled,
  Paper,
  Grid,
  FormControl,
  Avatar,
  Typography,
} from "@mui/material"
import { createTheme, ThemeProvider } from "@mui/material/styles"
import { useNavigate } from "react-router-dom"
import { Container } from "@mui/system"

const SignUp = () => {
  const navigate = useNavigate()
  const validationSchema = Yup.object().shape({
    id: Yup.string()
      .min(2, "닉네임은 최소 2글자 이상입니다!")
      .max(10, "닉네임은 최대 10글자입니다!")
      .required("아이디를 입력하세요!"),
    email: Yup.string()
      .email("올바른 이메일 형식이 아닙니다!")
      .required("이메일을 입력하세요!"),
    nickname: Yup.string()
      .min(2, "닉네임은 최소 2글자 이상입니다!")
      .max(10, "닉네임은 최대 10글자입니다!")
      .required("닉네임을 입력하세요!"),
    password: Yup.string()
      .min(8, "비밀번호는 최소 8자리 이상입니다")
      .max(16, "비밀번호는 최대 16자리입니다!")
      .required("패스워드를 입력하세요!"),
    password2: Yup.string()
      .oneOf([Yup.ref("password"), null], "비밀번호가 일치하지 않습니다!")
      .required("필수 입력 값입니다!"),
    phoneNumber: Yup.string().required("전화번호를 입력하세요!"),
  })

  const submit = async (values) => {
    const { email, id, nickname, password, phoneNumber } = values
    console.log(email, id, nickname, password)
    try {
      await axios.post("http://j7a504.p.ssafy.io:8080/user", {
        email: email,
        id: id,
        nickname: nickname,
        password: password,
        phoneNumber: phoneNumber,
      })
      toast.success(
        <h3>
          회원가입이 완료되었습니다.
          <br />
          로그인 하세요😎
        </h3>,
        {
          position: "center",
          autoClose: 2000,
        }
      )
      setTimeout(() => {
        navigate("/login")
      }, 2000)
    } catch (e) {
      // 서버에서 받은 에러 메시지 출력
      console.log(e.response.data.message)
      if (e.response.message === undefined) {
        toast.error("이미 가입된 유저입니다!" + "😮", {
          position: "top-center",
        })
      } else
        toast.error(e.response.data.message + "😭", {
          position: "top-center",
        })
    }
  }

  return (
    <Background>
      <SignupPaper>
        <ThemeProvider theme={theme}>
          <Box align="center" sx={{ mb: 2 }}>
            <Avatar sx={{ m: 1, bgcolor: "secondary.main" }} />
            <Typography align="center" variant="h4">
              Signup
            </Typography>
          </Box>
          <Formik
            initialValues={{
              email: "",
              nickname: "",
              id: "",
              password: "",
              phoneNumber: "",
            }}
            validationSchema={validationSchema}
            onSubmit={submit}
            validateOnMount={true}
          >
            {({ values, handleSubmit, handleChange, errors }) => (
              <form onSubmit={handleSubmit} autoComplete="off">
                <FormControl component="fieldset" variant="standard">
                  <ToastContainer />
                  <SignupGrid container spacing={2}>
                    <Grid item xs={12}>
                      <TextField
                        value={values.id}
                        fullWidth
                        name="id"
                        variant="outlined"
                        onChange={handleChange}
                        label="ID*"
                      />
                      <ErrorBox>{errors.id}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        value={values.email}
                        fullWidth
                        name="email"
                        variant="outlined"
                        onChange={handleChange}
                        label="이메일 주소*"
                      />
                      <ErrorBox>{errors.email}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        value={values.nickname}
                        fullWidth
                        name="nickname"
                        variant="outlined"
                        onChange={handleChange}
                        label="닉네임*"
                      />
                      <ErrorBox>{errors.nickname}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        value={values.phoneNumber}
                        fullWidth
                        name="phoneNumber"
                        variant="outlined"
                        onChange={handleChange}
                        label="전화번호*"
                      />
                      <ErrorBox>{errors.phoneNumber}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        value={values.password}
                        fullWidth
                        name="password"
                        variant="outlined"
                        type="password"
                        onChange={handleChange}
                        label="비밀번호 (8자리 이상)*"
                      />
                      <ErrorBox>{errors.password}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <TextField
                        value={values.password2}
                        fullWidth
                        name="password2"
                        variant="outlined"
                        type="password"
                        onChange={handleChange}
                        label="비밀번호 확인*"
                      />
                      <ErrorBox>{errors.password2}</ErrorBox>
                    </Grid>
                    <Grid item xs={12}>
                      <Button
                        color="primary"
                        variant="contained"
                        fullWidth
                        type="submit"
                        sx={{ mb: 2 }}
                        size="large"
                      >
                        회원가입
                      </Button>
                    </Grid>
                  </SignupGrid>
                </FormControl>
              </form>
            )}
          </Formik>
        </ThemeProvider>
      </SignupPaper>
    </Background>
  )
}

export default SignUp

const Background = styled(Container)({
  width: "40vw",
  height: "100vh",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  alignContent: "center",
})

const SignupPaper = styled(Paper)({
  width: "25vw",
  height: "70vh",
  padding: "5%",
  justifyContent: "center",
  borderRadius: "10px",
  // component: "main",
  // maxWidth: "xs",
})

const theme = createTheme({
  palette: {
    primary: {
      main: "#b59b89",
      darker: "#322725",
    },
    secondary: {
      main: "#b59b89",
      contrastText: "#b59b89",
    },
  },
})

const ErrorBox = styled(Box)({
  color: "gray",
  fontSize: "0.8em",
  marginLeft: "7px",
})

const SignupGrid = styled(Grid)({
  width: "100%",
})
