import {SET_FORM_TYPE, SET_ITEM, TOGGLE_FORM} from "@/store/modules/form/mutation-types";

export default {
    openForm({commit}) {
        commit(TOGGLE_FORM, true);
    },

    closeForm({commit}) {
        commit(TOGGLE_FORM, false);
    },

    setFormType({commit}, type) {
        commit(SET_FORM_TYPE, {type: type});
    },

    setFormItem({commit}, item) {
        commit(SET_ITEM, {item: item})
    }
}