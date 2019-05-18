<template>
    <div class="list-block list-item css-row">
        <div class="css-column">
            <p class="item-title">{{item.title}}</p>
        </div>

        <div class="item-date css-column align-right">
            <i class="far fa-calendar-alt"><span>{{ localeDate }}</span></i>
        </div>

        <div class="item-controls css-column align-right">
            <a class="button item-btn comment-btn" @click.prevent="onCommentClick">
                <i class="far fa-comment-dots"></i>
            </a>

            <a class="button item-btn edit-btn" @click.prevent="onEditClick">
                <i class="fas fa-pen"></i>
            </a>

            <a class="button item-btn delete-btn" @click.prevent="onDeleteClick">
                <i class="far fa-trash-alt"></i>
            </a>
        </div>
    </div>
</template>

<script>
    import {DATE_FORMAT} from "@/util/constants";

    export default {
        name: "TaskItemComponent",

        props: {
            item: {
                type: Object,
                required: true
            }
        },

        computed: {
            localeDate() {
                return this.$moment(this.item.dueDate).local().format(DATE_FORMAT);
            }
        },

        methods: {
            onCommentClick() {
              this.$emit("comment", this.item);
            },

            onEditClick() {
                this.$emit('edit', this.item);
            },

            onDeleteClick() {
                this.$emit('delete', this.item.id);
            }
        }
    }
</script>

<style scoped lang="scss">
    @import '../scss/variables';

    $control-btn-number: 3;

    .list-item {
        &:hover {
            background-color: darken($body-bg-color, 5%);
        }

        .item-title {
            @extend %text-ellipsis;
            font-size: 15px;
        }

        .item-date{
            span {
                margin-left: 8px;
                font-size: 15px;
                color: darken($body-bg-color, 15%);
            }
        }

        .item-controls {
            width: calc(35px * #{$control-btn-number});

            .item-btn {
                width: 25px;
                height: 25px;
            }
        }
    }
</style>