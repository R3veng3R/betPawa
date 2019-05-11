<template>
    <div class="list-block list-item flex-row">
        <p class="item-title">{{item.title}}</p>

        <div class="item-date">
            <i class="far fa-calendar-alt"><span>{{item.todoDate}}</span></i>
        </div>

        <div class="item-controls">
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
    export default {
        name: "TaskItemComponent",

        props: {
            item: {
                type: Object,
                required: true
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

    .list-item {
        .item-title {
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
            .item-btn {
                width: 25px;
                height: 25px;
            }
        }
    }
</style>