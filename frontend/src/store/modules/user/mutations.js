import {SET_AUTHENTICATED, SET_USER} from "./mutation-types";

export default {
    [SET_USER]: (state, user) => state.user = user,
    [SET_AUTHENTICATED]: (state, status) => state.isAuthenticated = status

}
