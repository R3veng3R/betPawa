import {
    SET_FORM_TYPE,
    SET_ITEM,
    SET_LOADING,
    SET_TASK_COMMENTS,
    TOGGLE_FORM
} from "@/store/modules/form/mutation-types";
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

    addComment({dispatch, commit}, commentDTO) {
        return CommentService.addComment(commentDTO)
            .then(data => { dispatch('getTaskComments', data.id); })
            .catch(error => console.warn(error.message))
    },

    getTaskComments({ commit }, taskId) {
        return CommentService.getTaskCommentsByTaskId(taskId)
            .then(data => commit(SET_TASK_COMMENTS, data ))
            .catch(error => console.warn(error.message))
    }
}