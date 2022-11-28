import { createSlice, current } from "@reduxjs/toolkit"

const lastTrips = createSlice({
  name: "lastTrips",
  initialState: [],
  reducers: {
    setLastTrip(state, { payload }) {
      return payload
    },
  },
})

export const { setLastTrip } = lastTrips.actions
export default lastTrips.reducer
