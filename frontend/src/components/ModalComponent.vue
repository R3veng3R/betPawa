<template>
    <div class="modal opened">
        <div class="modal-content">
            <div class="modal-header">
                <i class="fas fa-times close" @click.prevent="onCloseClick"></i>
                <h3><slot name="header"></slot></h3>
            </div>

            <div class="modal-body">
                <slot name="body"></slot>
            </div>

            <div class="modal-footer">
                <slot name="footer"></slot>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ModalComponent",

        methods: {
            onCloseClick() {
                this.$emit('modalClosed');
            },
        }
    }
</script>

<style scoped lang="scss">
    @import '../scss/modal';
</style>