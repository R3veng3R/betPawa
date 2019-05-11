import {SET_TASK_LIST} from "./mutation-types";

export default {
    [SET_TASK_LIST]: (state, payload) => {
        if (!payload || !payload.taskList) {
            state.taskList = [];
            return;
        }

        state.taskList = payload.taskList;
    }
}
