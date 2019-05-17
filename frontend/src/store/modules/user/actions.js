import {SET_AUTHENTICATED, SET_USER} from "@/store/modules/user/mutation-types";
import LoginService from "@/services/login.service";

export default {
    setUser({commit}, user) {
        if (!user) { return; }

        commit(SET_USER, user);
        commit(SET_AUTHENTICATED, true);
    },

    auth({dispatch, commit}, email) {
        return LoginService.authenticate(email)
            .then(user => {
                dispatch('setUser', user );
                return user;
            })
            .catch(error => { return error; } )
    }
}
