import TaskService from '@/services/tasks.service';
import {SET_TASK_LIST} from "@/store/modules/task/mutation-types";

export default {
    getTasks ({ commit }) {
        return TaskService.getTasks()
            .then(list => commit(SET_TASK_LIST, {taskList: list}))
            .catch(error => console.warn(error.message));
    }
}
