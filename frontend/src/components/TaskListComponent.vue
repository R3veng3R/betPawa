<template>
    <div class="task-list-wrapper">
        <div class="header-block list-block flex-row">
            <div class="header">{{headerString}}</div>
            <a class="button" @click.prevent="addTask">{{addTaskString}}</a>
        </div>

        <template v-if="!isEmptyList">
            <task-item-component v-for="task in taskList"
                                 :key="task.id"
                                 :item="task"
                                 @delete="onDelete"
                                 @comment="onComment"
                                 @edit="onEdit">
            </task-item-component>
        </template>
        <template v-else>
            <div class="empty-list-block">{{emptyListString}} {{addTaskString}}</div>
        </template>
    </div>
</template>

<script>
    import { mapGetters, mapActions } from 'vuex';
    import {SET_TASK_LIST} from "@/store/modules/task/mutation-types";
    import TaskItemComponent from "@/components/TaskItemComponent";
    import {FORM_TYPE_EDIT, FORM_TYPE_READ} from "@/util/constants";

    export default {
        name: "TaskListComponent",
        components: {TaskItemComponent},

        computed: {
            ...mapGetters({
                taskList: 'getTaskList',
                isEmptyList: 'isEmptyTaskList'
            })
        },

        data() {
            return {
                headerString: 'PawaTasks:',
                emptyListString: 'You do not have any tasks:',
                addTaskString: 'Add new task',
            }
        },

        methods: {
            addTask() {
                let payload = {
                    taskList: [
                        {
                            id: 1,
                            title: 'Test me',
                            description: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. ' +
                                'Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, ' +
                                'when an unknown printer took a galley of type and scrambled it to make a type specimen book.',
                            todoDate: '12/12/12',
                            priority: 'LOW',
                            comments: []
                        }
                    ]
                };

                if (!this.isEmptyList) {
                    this.$store.commit(SET_TASK_LIST, {taskList: []});
                } else {
                    this.$store.commit(SET_TASK_LIST, payload);
                }
            },

            onComment(item) {
                this.setFormType(FORM_TYPE_READ);
                this.setFormItem(item);
                this.openForm();
                console.log('comment obj: ', item);
            },

            onEdit(item) {
                this.setFormType(FORM_TYPE_EDIT);
                this.setFormItem(item);
                this.openForm();
                console.log('edit obj: ', item);
            },

            onDelete(id) {
                console.log('delete', id);
            },

            ...mapActions(['openForm', 'setFormType', 'setFormItem'])
        }
    }
</script>

<style scoped lang="scss">
    @import "../scss/variables";

    .task-list-wrapper {
        margin-top: 10px;
        border: 1px solid darken($body-bg-color, 10%);
        min-height: 800px;
        background-color: white;
        padding-right: 15px;
        padding-left: 15px;
        border-radius: 5px;

        .list-block {
            height: 50px;
            box-shadow: 0 1px 0 0 darken($body-bg-color, 10%);
        }

        .empty-list-block {
            padding-top: 10px;
            font-size: 12px;
            font-weight: normal;
            color: dimgray;
        }
    }
</style>