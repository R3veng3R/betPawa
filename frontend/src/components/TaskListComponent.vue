<template>
    <div class="task-list-wrapper">
        <div class="header-block list-block flex-row">
            <div class="header">{{headerString}}: {{ getUserName() }}</div> <!-- TODO: FIX MOBILE VIEW  (320 x 600) -->
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
    import {mapActions, mapGetters} from 'vuex';
    import TaskItemComponent from "@/components/TaskItemComponent";
    import {DEFAULT_TASK_OBJ, FORM_TYPE_CREATE, FORM_TYPE_EDIT, FORM_TYPE_READ} from "@/util/constants";

    export default {
        name: "TaskListComponent",
        components: {TaskItemComponent},

        computed: {
            ...mapGetters({
                taskList: 'getTaskList',
                isEmptyList: 'isEmptyTaskList',
                user: 'getUser'
            })
        },

        beforeMount() {
            this.getTasks();
        },

        data() {
            return {
                headerString: 'PawaTasks',
                emptyListString: 'You do not have any tasks:',
                addTaskString: 'Add a new task',
            }
        },

        methods: {
            addTask() {
                this.setFormType(FORM_TYPE_CREATE);
                this.setFormItem(DEFAULT_TASK_OBJ);
                this.openForm();
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

            onDelete(id) {  // TODO: create confirm box
                this.deleteTask(id);
            },

            getUserName() {
                return this.user.name + ' ' + this.user.lastName.charAt(0).toUpperCase() + '.';
            },

            ...mapActions(['openForm', 'setFormType', 'setFormItem', 'getTasks', 'deleteTask'])
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

        .header-block {
            .header {
                @extend %text-ellipsis;
            }
        }

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