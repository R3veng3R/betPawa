import {ErrorWrapper} from "@/util/helpers";
import BaseService from "@/services/base.service";

const TASK_LIST_API = '/api/task/list';
const TASK_API = '/api/task';

class TasksService extends BaseService {

    getTasks() {
        return new Promise((resolve, reject) => {
            return this.request().get(TASK_LIST_API)
                .then( response => resolve(response.data))
                .catch(error => {
                    reject(new ErrorWrapper(error))
                });
        });
    }

    addTask(taskDTO) {
        return new Promise( (resolve, reject) => {
            return this.request().post(TASK_API, taskDTO)
                .then( response => resolve(response.data))
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }

    deleteTask(id) {
        return new Promise( (resolve, reject) => {
            return this.request().delete(TASK_API + "/" + id)
                .then( response => resolve(response.data))
                .catch(error => {reject(new ErrorWrapper(error))})
        });
    }
}

export default new TasksService();