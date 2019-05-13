<template>
    <div class="form-wrapper">

        <template v-if="isOpened">
            <!-- EDIT FORM -->
            <modal-component v-if="isEdit || isCreate" @modalClosed="closeForm">
                <template slot="header">
                    < {{ taskItem.title }} >
                </template>

                <template slot="body">
                    <div class="input-field">
                        <label for="title">{{ titleLabel }}:</label>
                        <input class="input" type="text" id="title" v-model="taskItem.title"/>
                    </div>

                    <div class="input-field">
                        <label for="description">{{ descriptionLabel }}:</label>
                        <textarea cols="30" rows="10" id="description" v-model="taskItem.description"></textarea>
                    </div>

                    <div class="input-field">
                        <label for="priority">{{ priorityLabel }}:</label>
                        <select class="input custom-select" id="priority" v-model="taskItem.priority">
                            <option v-for="priority in priorityList"
                                    :value="priority.value">
                                {{ priority.label }}
                            </option>
                        </select>
                    </div>

                    <div class="input-field">
                        <label>{{ setDateLabel }}:</label>
                        <input class="date-input" type="number" min="1" max="31" placeholder="DD" ref="day" :value="getLocalDate('D')"/>
                        <input class="date-input" type="number" min="1" max="12" placeholder="MM" ref="month" :value="getLocalDate('M')"/>
                        <input class="date-input year" type="number" min="2018" placeholder="YYYY" ref="year" :value="getLocalDate('YYYY')"/>
                    </div>

                    <div class="input-field">
                        <label for="comments">{{ commentsLabel }}:</label>
                        <textarea cols="30" rows="10" id="comments" maxlength="500"></textarea>
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

                    <div class="comments-wrapper">

                    </div>
                </template>

                <template slot="footer">
                    <a class="button" @click.prevent="" style="float: right">Add comment</a>
                    <div class="comment-input-wrapper">
                        <input class="comment-input" type="text" :placeholder="commentPlaceholder" />
                    </div>
                </template>
            </modal-component>
        </template>
    </div>
</template>

<script>
    import { mapGetters, mapActions } from 'vuex';
    import {
        FORM_PRIORITY_LIST,
        FORM_TYPE_CREATE,
        FORM_TYPE_EDIT,
        FORM_TYPE_READ,
        DATE_FORMAT,
        DB_DATE_FORMAT
    } from "@/util/constants";
    import ModalComponent from "@/components/ModalComponent";

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
                taskItem: null,
                dateFormat: DATE_FORMAT,
                formDate: {
                    month: 1,
                    day: 1,
                    year: 2018
                }
            }
        },

        computed: {
            ...mapGetters({
                formType: 'getFormType',
                formItem: 'getFormItem',
                isOpened: 'getFormOpenStatus'
            }),

            isRead() {
                return this.formType === FORM_TYPE_READ;
            },

            isEdit() {
                return this.formType === FORM_TYPE_EDIT;
            },

            isCreate() {
                return this.formType === FORM_TYPE_CREATE;
            }
        },

        methods: {
            getPriorityText(value) {
                value = value.toLowerCase();
                return value.charAt(0).toUpperCase() + value.slice(1);
            },

            getLocalDate(format) {
                return this.$moment(this.taskItem.dueDate).local().format(format);
            },

            setTaskDate() {
                let month = this.$refs.month.value;
                let day =  this.$refs.day.value;
                let year = this.$refs.year.value;

                let date = this.$moment(year + '-' + month + '-' + day, DB_DATE_FORMAT);
                this.taskItem.dueDate = date;
            },

            onOkClick() {
                this.setTaskDate();
                this.addNewTask(this.taskItem);
            },

            ...mapActions([
                'closeForm', 'addNewTask'
            ])
        },

        watch: {
            isOpened: function() {
                if (this.isRead) { return; }

                // WE DON'T WANT REACTIVITY ON ORIGINAL ITEM FROM STORE
                // JUST IN CASE USER DECIDES TO CANCEL THE PROCESS
                this.taskItem = JSON.parse(JSON.stringify(this.formItem));
            }
        }
    }
</script>

<style scoped lang="scss">
    @import "../scss/form";
</style>