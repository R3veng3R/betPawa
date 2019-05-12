import $axios from "axios";
import {ErrorWrapper} from "@/util/helpers";

class TasksService {

    getTasks () {
        return new Promise((resolve, reject) => {
            return $axios.get('/api/task/list')
                .then( response => resolve(response.data))
                .catch(error => {
                    reject(new ErrorWrapper(error))
                });
        });
    }
}

export default new TasksService();