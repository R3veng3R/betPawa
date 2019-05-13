<template>
    <div class="loader-overlay" v-if="isLoading">
        <div class="lds-ellipsis">
            <p>Loading...</p>
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex';

    export default {
        name: "LoaderComponent",

        computed: {
            ...mapGetters([ 'isLoading' ])
        }
    }
</script>

<style scoped lang="scss">
    @import "../scss/loader";
</style>