<template>
    <div class="form-wrapper">

        <template v-if="isOpened">
            <!-- EDIT FORM -->
            <modal-component v-if="isEdit || isCreate" @modalClosed="closeForm">
                <template slot="header">
                     {{ taskDTO.title }}
                </template>

                <template slot="body">
                    <div class="form-container">
                        <div class="input-field">
                            <label for="title">{{ titleLabel }}:</label>
                            <input class="input" type="text" id="title" v-model="taskDTO.title"/>
                        </div>

                        <div class="input-field">
                            <label for="description">{{ descriptionLabel }}:</label>
                            <textarea cols="30" rows="10" id="description" v-model="taskDTO.description"></textarea>
                        </div>

                        <div class="input-field">
                            <label for="priority">{{ priorityLabel }}:</label>
                            <select class="input custom-select" id="priority" v-model="taskDTO.priority">
                                <option v-for="priority in priorityList"
                                        :value="priority.value">
                                    {{ priority.label }}
                                </option>
                            </select>
                        </div>

                        <div class="input-field">
                            <label>{{ setDateLabel }}:</label>
                            <input class="date-input" type="number" min="1" max="31" placeholder="DD" ref="day"
                                   :value="getLocalDate('D')"/>
                            <input class="date-input" type="number" min="1" max="12" placeholder="MM" ref="month"
                                   :value="getLocalDate('M')"/>
                            <input class="date-input year" type="number" min="2018" placeholder="YYYY" ref="year"
                                   :value="getLocalDate('YYYY')"/>
                        </div>

                        <div class="input-field">
                            <label for="comments">{{ commentsLabel }}:</label>
                            <textarea cols="30" rows="10" id="comments" maxlength="500"
                                      v-model="taskDTO.comment"></textarea>
                        </div>
                    </div>
                </template>

                <template slot="footer">
                    <a class="button" @click.prevent="onOkClick">Edit/Create</a>
                </template>
            </modal-component>

            <!-- READ FORM -->
            <modal-component v-if="isRead" @modalClosed="closeForm">
                <template slot="header">
                    {{ formItem.title }}
                </template>

                <template slot="body">
                    <div class="form-container border-bottom">
                        <p class="description">{{ formItem.description }}</p>

                        <div class="flex-row">
                            <div class="col col-date">
                                <span class="item-label">{{ dateLabel }}:</span>
                                {{ formItem.dueDate | moment(dateFormat)}}
                            </div>

                            <div class="col col-priority">
                                <span class="item-label">{{ priorityLabel }}:</span>
                                {{ getPriorityText(formItem.priority) }}
                            </div>
                        </div>
                    </div>

                    <div class="comments-wrapper">
                        <div class="comment" v-for="comment in comments" :key="comment.id">
                            <p>
                                <span class="name">{{ comment.user.name + ' ' + formatLastName(comment.user.lastName) }}.</span>
                                <span class="create-date">{{ formatDate(comment.createDate) }}</span>
                            </p>
                            <p class="comment-text">
                                {{ comment.comment }}
                            </p>
                        </div>
                    </div>
                </template>

                <template slot="footer">
                    <a class="button" @click.prevent="onAddCommentClick" style="float: right">Add comment</a>
                    <div class="comment-input-wrapper">
                        <input class="comment-input" type="text" :placeholder="commentPlaceholder"
                               v-model="taskDTO.comment"/>
                    </div>
                </template>
            </modal-component>
        </template>
    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex';
    import {
        DATE_FORMAT,
        DB_DATE_FORMAT,
        FORM_PRIORITY_LIST,
        FORM_PRIORITY_MEDIUM,
        FORM_TYPE_CREATE,
        FORM_TYPE_EDIT,
        FORM_TYPE_READ
    } from "@/util/constants";
    import ModalComponent from "@/components/ModalComponent";
    import {SET_TASK_COMMENTS} from "@/store/modules/form/mutation-types";

    export default {
        name: "FormComponent",
        components: {ModalComponent},

        data() {
            return {
                priorityList: FORM_PRIORITY_LIST,
                commentsLabel: 'Comments',
                titleLabel: 'Title',
                priorityLabel: 'Priority',
                descriptionLabel: 'Description',
                commentPlaceholder: 'Write a comment...',
                dateLabel: 'Date',
                setDateLabel: 'Set due date',
                dateFormat: DATE_FORMAT,

                taskDTO: {
                    title: '',
                    description: '',
                    priority: FORM_PRIORITY_MEDIUM,
                    comment: '',
                    dueDate: '',
                    userId: '',
                    id: null
                }
            }
        },

        computed: {
            ...mapGetters({
                formType: 'getFormType',
                formItem: 'getFormItem',
                isOpened: 'getFormOpenStatus',
                user: 'getUser',
                comments: 'getComments'
            }),

            isRead() {
                return this.formType === FORM_TYPE_READ;
            },

            isEdit() {
                return this.formType === FORM_TYPE_EDIT;
            },

            isCreate() {
                return this.formType === FORM_TYPE_CREATE;
            },

            isEmptyComment() {
                return this.taskDTO.comment.length === 0;
            }
        },

        methods: {
            getPriorityText(value) {
                value = value.toLowerCase();
                return value.charAt(0).toUpperCase() + value.slice(1);
            },

            getLocalDate(format) {
                return this.$moment(this.taskDTO.dueDate).local().format(format);
            },

            setTaskData() {
                let month = this.$refs.month.value;
                let day = this.$refs.day.value;
                let year = this.$refs.year.value;

                let date = this.$moment(year + '-' + month + '-' + day, DB_DATE_FORMAT);
                this.taskDTO.dueDate = date;
                this.taskDTO.userId = this.user.id;

                if (this.formItem.id) {
                    this.taskDTO.id = this.formItem.id;
                }
            },

            onOkClick() {
                this.setTaskData();
                this.addNewTask(this.taskDTO);
            },

            onAddCommentClick() {
                if (this.isEmptyComment) {
                    return;
                }

                let commentDTO = {
                    userId: this.user.id,
                    taskId: this.formItem.id,
                    comment: this.taskDTO.comment
                };

                this.addComment(commentDTO);
                this.taskDTO.comment = '';
            },

            formatLastName(lastname) {
                return lastname.charAt(0).toUpperCase();
            },

            formatDate(date) {
                let momentDate = this.$moment(date).local();
                let result = momentDate.fromNow();
                let at = ' at ' + momentDate.format('HH:mm');
                return result + at;
            },

            resetForm() {
                this.taskDTO.title = '';
                this.taskDTO.description = '';
                this.taskDTO.priority = FORM_PRIORITY_MEDIUM;
                this.taskDTO.comment = '';
                this.taskDTO.id = null;
            },

            setForm() {
                this.taskDTO.description = this.formItem.description;
                this.taskDTO.priority = this.formItem.priority;
                this.taskDTO.dueDate = this.formItem.dueDate;
                this.taskDTO.title = this.formItem.title;
            },

            ...mapActions([
                'closeForm', 'addNewTask', 'addComment', 'getTaskComments'
            ])
        },

        watch: {
            isOpened: function (newValue) {
                if (newValue === false) {
                    this.$store.commit(SET_TASK_COMMENTS, []);
                    this.resetForm();
                    return;
                }

                if (this.isRead) {
                    this.getTaskComments(this.formItem.id);
                    return;
                }

                this.setForm();
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../scss/form";
</style>