import { BrowserRouter, Route, Routes } from "react-router-dom"
import LoginPage from "../pages/users/loginPage"
import SignUpPage from "../pages/users/signUpPage"
import Mypage from "../pages/users/myPage"
import CourseDetailPage from "../pages/courseDetailPage"
import CreateCoursePage from "../pages/createCoursePage"
import MainPage from "../pages/mainPage"
import SurveyPage from "../pages/surveyPage"
import Logout from "../pages/users/logout"
import SurveyInfo from "../components/survey/surveyInfo"

const Router = () => {
  return (
    <Routes>
      <Route path="/" element={<MainPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/logout" element={<Logout />} />
      <Route path="/signup" element={<SignUpPage />} />
      <Route path="/course/:courseId" element={<CourseDetailPage />} />
      <Route path="/mypage" element={<Mypage />} />
      <Route path="/course/create" element={<CreateCoursePage />} />
      <Route path="/survey/*" element={<SurveyPage />} />
      <Route path="/survey/info" element={<SurveyInfo />} />
    </Routes>
  )
}

export default Router
