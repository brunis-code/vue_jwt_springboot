<template>
    <v-layout column>
        <v-text-field
                label="Логин"
                placeholder="login"
                v-model="username"
        />
        <v-text-field
                label="Пароль"
                placeholder="password"
                v-model="password"
                type="password"
                @keyup.enter="login"
        />
        <v-btn @click="login">
            Save
        </v-btn>
        <hr>
        <div style="color:red;">{{msg}}</div>
    </v-layout>
</template>

<script>
    export default {
        name: "LoginForm",
        data() {

            return {
                username: "", password: "",
                msg: "jwt auth"
            };
        },
        methods: {
            login() {
                this.$http
                    .post("/api/auth/signin", {
                        username: this.username,
                        password: this.password
                    })
                    .then(res => {
                        console.log("Успешная авторизация！");
                        console.log(res.data);
                        /**Token local Storage*/
                        const authorization = res.data.accessToken;
                        this.$store.state.token = authorization;
                        // localStorage.token = authorization;
                        this.msg = this.$store.state.token;
                        this.$router.replace('/')
                    })
                    .catch(error => {
                        console.log("Ошибка входа！");
                        console.log(error);
                        this.msg = error;
                    });
            }
        }
    }
</script>

<style scoped>

</style>