import store from '@/store';
import {SET_TASK_LIST} from "../../src/store/modules/task/mutation-types";

describe('Vuex store test suit: ------------------------', () => {

    let mockObj = {
        id: 1,
        title: '',
        description: '',
        todoDate: '',
        priority: 'LOW',
        comments: []
    };

    it('should add tasks to list', () => {
        let taskList = [
            Object.assign({}, mockObj),
            Object.assign({}, mockObj),
            Object.assign({}, mockObj)
        ];

        let payload = {
            taskList: taskList
        };

        store.commit(SET_TASK_LIST, payload);

        let resultList = store.getters.getTaskList;
        let expected = 3;
        let actual = resultList.length;

        expect(actual).toBe(expected);
    });

    it('should add empty list if tasks are null / undefined', () => {
        let taskList = undefined;
        let payload = {
            taskList: taskList
        };

        store.commit(SET_TASK_LIST, payload);

        let resultList = store.getters.getTaskList;
        let expected = 0;
        let actual = resultList.length;

        expect(actual).toBe(expected);
    });
});
