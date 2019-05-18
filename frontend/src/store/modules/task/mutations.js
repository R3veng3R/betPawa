import {SET_CURRENT_PAGE, SET_MAX_PAGES, SET_TASK_LIST} from "./mutation-types";

export default {
    [SET_TASK_LIST]: (state, payload) => {
        if (!payload || !payload.taskList) {
            state.taskList = [];
            return;
        }

        state.taskList = payload.taskList;
    },

    [SET_MAX_PAGES]: (state, totalPages) => {
        if (!totalPages) {
            state.maxPages = 1;
            return;
        }

        state.maxPages = totalPages;
    },

    [SET_CURRENT_PAGE]: (state, index) => { state.page = index }
}
