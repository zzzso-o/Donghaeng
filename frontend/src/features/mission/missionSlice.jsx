import { createSlice } from "@reduxjs/toolkit"
import { createAsyncThunk } from "@reduxjs/toolkit"

// const userData = {
//     userId,
//     userPw,
//     userName,
//     userEmail
// }

const missionSlice = createSlice({
  name: "mission",
  initialState: [],
  reducers: {
    setMission: (state, { payload }) => {
      return payload
    },
  },
})

export const { setMission } = missionSlice.actions
export default missionSlice.reducer
