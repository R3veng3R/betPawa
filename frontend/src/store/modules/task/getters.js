export default {
    getTaskList: (state) => { return state.taskList; },
    isEmptyTaskList: (state) => { return state.taskList.length === 0; },
    getMaxPages: (state) => { return state.maxPages; },
    getCurrentPage: (state) => { return state.page;}
}