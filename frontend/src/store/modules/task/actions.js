import TaskService from '@/services/tasks.service';
import {SET_CURRENT_PAGE, SET_MAX_PAGES, SET_TASK_LIST} from "@/store/modules/task/mutation-types";

export default {
    getTasks ({ commit, state }) {
        let paging = {
            page: state.page,
            pageSize: state.pageSize
        };

        return TaskService.getTasks(paging)
            .then(data => {
                commit(SET_MAX_PAGES, data.totalPages);
                commit(SET_TASK_LIST, {taskList: data.content});
            })
            .catch(error => console.warn(error.message));
    },

    addNewTask({ dispatch, commit }, taskDTO) {
        return TaskService.addTask(taskDTO)
            .then(response => {
                dispatch('closeForm');
                dispatch('getTasks');
            })
            .catch(error=> console.warn(error.message))
    },

    deleteTask({ dispatch, commit }, id) {
        return TaskService.deleteTask(id)
            .then(response => dispatch('getTasks'))
            .catch(error => console.warn(error.message))
    },

    setCurrentPage({ dispatch, commit, state }, index) {
        commit(SET_CURRENT_PAGE, index);
        dispatch('getTasks');
    }
}
