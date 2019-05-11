export default {
    getFormType: (state) => {
        return state.type;
    },

    getFormItem: (state) => {
        return state.item;
    },

    getFormOpenStatus: (state) => {
        return state.isOpened;
    }
}