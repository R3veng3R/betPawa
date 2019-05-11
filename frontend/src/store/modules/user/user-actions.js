import {SET_CURRENT_USER} from "./mutation-types";

export default {
    setUser({commit}) {
        commit(SET_CURRENT_USER);
    }
}