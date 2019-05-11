import {SET_CURRENT_USER} from "./mutation-types";

export default {
    [SET_CURRENT_USER] (state) {
        console.log('USER MUTATION: ', state);
    }
}