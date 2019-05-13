import {SET_ITEM, SET_FORM_TYPE, TOGGLE_FORM, SET_LOADING} from "@/store/modules/form/mutation-types";

export default {
    [SET_ITEM]: (state, payload) => state.item = payload.item,
    [SET_FORM_TYPE]: (state, payload) => state.type = payload.type,
    [TOGGLE_FORM]: (state, status) => state.isOpened = status,
    [SET_LOADING]: (state, status) => state.isLoading = status
}
