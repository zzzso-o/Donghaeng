import React, { useState, useEffect } from "react"
import PropTypes from "prop-types"
// import Badge from "../../components/myPage/badge"
import Info from "../../components/myPage/userInfo"
import LastTrip from "../../components/myPage/lastTrip"
import Album from "../../components/myPage/album"
import { Box, Paper, styled, Tab, Tabs, Typography } from "@mui/material"
import { useSelector, useDispatch } from "react-redux/es/exports"
import { setUserInfo } from "../../features/user/userSlice"
import interceptor from "../../api/interceptor"
import "../../App.css"
import PermIdentityIcon from "@mui/icons-material/PermIdentity"
import MapIcon from "@mui/icons-material/Map"
import { UserId } from "../../components/myPage/userInfo"

function TabPanel(props) {
  const { children, value, index, ...other } = props

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`vertical-tabpanel-${index}`}
      aria-labelledby={`vertical-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  )
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
}

const MyPage = () => {
  const [value, setValue] = useState(1)
  const [albumOpen, setAlbumOpen] = useState(false)
  const user = useSelector((state) => state.user)
  const dispatch = useDispatch()

  useEffect(() => {
    interceptor({
      url: "/user/info",
      method: "get",
    }).then((res) => {
      dispatch(setUserInfo(res.data))
    })
  }, [])

  const handleChange = (event, newValue) => {
    setValue(newValue)
  }
  return (
    <Container>
      <Background>
        <SideBar elevation={0}>
          <Profile>
            <Photo src={user.profileImage} alt="profile" />
            <Name>{user.nickname}</Name>
            <UserId>{`@${user.id}`}</UserId>
          </Profile>
          <hr color="lightGrey" width="85%"></hr>
          <MyTabs
            orientation="vertical"
            value={value}
            onChange={handleChange}
            indicatorColor="undefined"
            textColor="inherit"
          >
            <MyTab
              label="내 정보"
              value={1}
              icon={<PermIdentityIcon />}
              iconPosition="start"
            />
            {/* <MyTab label="뱃지" value={2} /> */}
            <MyTab
              label="지난 여행"
              value={3}
              icon={<MapIcon />}
              iconPosition="start"
              onClick={() => setAlbumOpen(false)}
            />
          </MyTabs>
        </SideBar>
        <TabPanel value={value} index={1}>
          <Info />
        </TabPanel>
        {/* <TabPanel value={value} index={2}>
        <Badge />
      </TabPanel> */}
        <TabPanel value={value} index={3}>
          {albumOpen === false ? (
            <LastTrip albumOpen={albumOpen} setAlbumOpen={setAlbumOpen} />
          ) : (
            <Album albumOpen={albumOpen} />
          )}
        </TabPanel>
      </Background>
    </Container>
  )
}

export default MyPage

const Container = styled(Box)({
  width: "100vw",
  height: "100vh",
  backgroundImage: "url(" + "img/jeju.jpg" + ")",
  backgroundRepeat: "no-repeat",
  backgroundSize: "cover",
  backgroundAttachment: "fixed",
  backgroundPosition: "top center",
})

const Background = styled(Box)({
  display: "flex",
  paddingTop: "8vh",
  justifyContent: "center",
  alignItems: "center",
  width: "100%",
  height: "92%",
  backgroundColor: "rgba(0, 0, 0, 0.4)",
  position: "absolute",
})

const SideBar = styled(Paper)({
  height: "80vh",
  width: "18vw",
  display: "flex",
  flexDirection: "column",
  justifyContent: "space-evenly",
  alignItems: "space-evenly",
  borderRadius: 5,
  // background: "linear-gradient(135deg, white, chocolate)",
})

const Profile = styled(Box)({
  display: "flex",
  flexDirection: "column",
  justifyContent: "center",
  alignItems: "center",
})

export const Photo = styled("img")({
  borderRadius: "50%",
  width: "200px",
  height: "200px",
  margin: "2rem",
})

export const Name = styled(Typography)({
  color: "dark",
  fontWeight: "bold",
  fontSize: 30,
  fontFamily: "HallymGothic-Regular",
})

const MyTabs = styled(Tabs)({})

const MyTab = styled(Tab)({
  color: "#1976D2",
  fontSize: 16,
  fontWeight: "bold",
  borderRadius: 10,
  fontFamily: "HallymGothic-Regular",
})
