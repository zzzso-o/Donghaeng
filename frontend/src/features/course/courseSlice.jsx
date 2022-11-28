import { createSlice } from "@reduxjs/toolkit"

export const initialState = {
  tripNo: "",
  tripName: "",
  startDate: "",
  endDate: "",
  placeList: [],
}

const courseListSlice = createSlice({
  name: "courseList",
  initialState,
  reducers: {
    fetchCourseList: (state, action) => {
      state.tripNo = action.payload.tripNo
      state.tripName = action.payload.tripName
      state.startDate = action.payload.startDate
      state.endDate = action.payload.endDate
      if (state.placeList === null) {
        state.placeList = [...state.placeList, action.payload.placeList]
      } else {
        state.placeList = []
        state.placeList = [...state.placeList, action.payload.placeList]
      }
    },
  },
})

const actions = courseListSlice
export const fetchProfile = actions
export default courseListSlice.reducer
