import {
    SET_ITEM,
    SET_FORM_TYPE,
    TOGGLE_FORM,
    SET_LOADING,
    SET_TASK_COMMENTS
} from "@/store/modules/form/mutation-types";

export default {
    [SET_ITEM]: (state, payload) => state.item = payload.item,
    [SET_FORM_TYPE]: (state, payload) => state.type = payload.type,
    [TOGGLE_FORM]: (state, status) => state.isOpened = status,
    [SET_LOADING]: (state, status) => state.isLoading = status,
    [SET_TASK_COMMENTS]: (state, commentList) => {
        if (!commentList)  {
            state.comments = [];
        }

        state.comments = commentList;
    }
}
