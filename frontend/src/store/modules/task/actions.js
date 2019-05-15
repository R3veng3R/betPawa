import TaskService from '@/services/tasks.service';
import {SET_TASK_LIST} from "@/store/modules/task/mutation-types";

export default {
    getTasks ({ commit }) {
        return TaskService.getTasks()
            .then(list => commit(SET_TASK_LIST, {taskList: list}))
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
    }
}
