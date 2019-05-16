<template>
    <div class="container">
        <login-form-component v-if="!isAuthenticated" />

        <template v-else>
            <task-list-component />
            <form-component />
            <loader-component />
        </template>
    </div>
</template>

<script>
    import TaskListComponent from "@/components/TaskListComponent";
    import FormComponent from "@/components/FormComponent";
    import {mapGetters} from "vuex";
    import LoaderComponent from "@/components/LoaderComponent";
    import LoginFormComponent from "@/components/LoginFormComponent";

    export default {
        name: "App",
        components: {LoginFormComponent, LoaderComponent, FormComponent, TaskListComponent},

        computed: {
            ...mapGetters([ 'isAuthenticated' ])
        },
    }
</script>

<style scoped lang="scss">
    .container {
        max-width: 640px;
        margin: 0 auto;
    }
</style>