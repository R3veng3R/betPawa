import {SET_FORM_TYPE, SET_ITEM, SET_LOADING, TOGGLE_FORM} from "@/store/modules/form/mutation-types";
import CommentService from '@/services/comment.service';

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
    },

    setLoading({commit}, status) {
        commit(SET_LOADING, status);
    },

    addComment({commit}, comment) {
        return CommentService.addComment(comment)
            .then(response => console.log('Comment added!'))
            .catch(error => console.warn(error.message))
    }
}