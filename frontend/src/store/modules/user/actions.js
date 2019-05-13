import {SET_USER} from "@/store/modules/user/mutation-types";

export default {
    setUser({commit}, user) {
        if (!user) { return; }
        commit(SET_USER, user);
    }
}
