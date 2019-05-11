<template>
    <div class="form-wrapper">

        <template v-if="isOpened">
            <!-- EDIT FORM -->
            <modal-component v-if="isEdit" @modalClosed="closeForm">
                <template slot="header">
                    EDIT FORM
                </template>

                <template slot="body">
                    <div class="input-field">
                        <label for="title">{{ titleLabel }}:</label>
                        <input class="input" type="text" id="title" />
                    </div>

                    <div class="input-field">
                        <label for="description">{{ descriptionLabel }}:</label>
                        <textarea cols="30" rows="10" id="description"></textarea>
                    </div>

                    <div class="input-field">
                        <label for="priority">{{ priorityLabel }}:</label>
                        <select class="input custom-select" id="priority">
                            <option v-for="priority in priorityList"
                                    :value="priority.value">
                                {{ priority.label }}
                            </option>
                        </select>
                    </div>

                    <div class="input-field">
                        <label>{{ setDateLabel }}:</label>
                        <input class="date-input" type="number" min="1" max="31"  placeholder="DD"/>
                        <input class="date-input" type="number" min="1" max="12" placeholder="MM"/>
                        <input class="date-input year" type="number" min="2018" placeholder="YYYY"/>
                    </div>

                    <div class="input-field">
                        <label for="comments">{{ commentsLabel }}:</label>
                        <textarea cols="30" rows="10" id="comments"></textarea>
                    </div>
                </template>

                <template slot="footer">
                    <a class="button" @click.prevent="">Edit/Create</a>
                </template>
            </modal-component>


            <!-- READ FORM -->
            <modal-component v-if="!isEdit" @modalClosed="closeForm">
                <template slot="header">
                    {{ formItem.title }}
                </template>

                <template slot="body">
                    <p class="description">{{ formItem.description }}</p>


                    <div class="flex-row">
                        <div class="col col-date">
                            <span class="item-label">{{ dateLabel }}:</span>
                            {{ formItem.todoDate }}
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
    import {FORM_PRIORITY_LIST, FORM_TYPE_EDIT} from "@/util/constants";
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
                setDateLabel: 'Set due date'
            }
        },

        computed: {
            ...mapGetters({
                formType: 'getFormType',
                formItem: 'getFormItem',
                isOpened: 'getFormOpenStatus'
            }),

            isEdit() {
                return this.formType === FORM_TYPE_EDIT;
            }
        },

        methods: {
            getPriorityText(value) {
                value = value.toLowerCase();
                return value.charAt(0).toUpperCase() + value.slice(1);
            },

            ...mapActions([
                'closeForm'
            ])
        }
    }
</script>

<style scoped lang="scss">
    @import "../scss/form";
</style>