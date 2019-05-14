<template>
    <div class="container">
        <task-list-component />
        <form-component />
        <loader-component />
    </div>
</template>

<script>
    import TaskListComponent from "@/components/TaskListComponent";
    import FormComponent from "@/components/FormComponent";
    import {mapActions} from "vuex";
    import LoaderComponent from "@/components/LoaderComponent";

    export default {
        name: "App",
        components: {LoaderComponent, FormComponent, TaskListComponent},

        created() {
            this.setupUser();
        },

        methods: {
            setupUser() {
                let user = this.$cookies.get("user");

                if (!user) {
                    user = {
                        id: 1,
                        name: 'Aleksei',
                        lastName: "Jermatsenkov"
                    }

                    this.$cookies.set("user", user);
                }

                this.setUser(user);
            },

            ...mapActions(['setUser'])
        }
    }
</script>

<style scoped lang="scss">
    .container {
        max-width: 640px;
        margin: 0 auto;
    }
</style>