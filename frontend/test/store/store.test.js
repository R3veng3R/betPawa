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
        let actual = resultList.length;

        expect(actual).toBe(3);
    });

    it('should add empty list if tasks are null / undefined', () => {
        let taskList = undefined;
        let payload = {
            taskList: taskList
        };

        store.commit(SET_TASK_LIST, payload);

        let resultList = store.getters.getTaskList;
        let actual = resultList.length;

        expect(actual).toBe(0);
    });


    it('should authenticate and add user to store', () => {
       let user = {
           id: 1,
           name: 'TestUser',
           lastName: 'MyTestLastName'
       };

       store.dispatch('setUser', user);
       let actualUser = store.getters.getUser;
       let actualAuthenticate = store.getters.isAuthenticated ;

       expect(actualUser).toBe(user);
       expect(actualAuthenticate).toBe(true);
    });
});
