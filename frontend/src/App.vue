<template>
    <div class="container">
        <task-list-component />
        <form-component />
    </div>
</template>

<script>
    import TaskListComponent from "@/components/TaskListComponent";
    import FormComponent from "@/components/FormComponent";
    export default {
        name: "App",
        components: {FormComponent, TaskListComponent},
    }
</script>

<style scoped lang="scss">
    .container {
        max-width: 640px;
        margin: 0 auto;
    }
</style>