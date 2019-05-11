export default {
    getTaskList: (state) => {
        return state.taskList;
    },

    isEmptyTaskList: (state) => {
        return state.taskList.length === 0;
    }
}