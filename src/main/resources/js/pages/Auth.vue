<template>
    <v-container>
       <login-form/>
    </v-container>
</template>

<script>
    import LoginForm from "components/LoginForm.vue";
    export default {
        name: 'Auth',
        components: {LoginForm},
        comments:{

        }
    }
</script>

<style scoped>

</style>
