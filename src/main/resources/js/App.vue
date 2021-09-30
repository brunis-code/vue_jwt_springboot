<template>
    <v-app id="app">
        <v-system-bar app>
            <!-- -->
        </v-system-bar>
        <v-app-bar app>
            <v-toolbar-title>BrunisCode</v-toolbar-title>

            <v-btn flat
                   v-if="currentUser"
                   :disabled="$route.path === '/'|| $route.path === '/home'"
                   @click="$router.push('/')"

            >
                <font-awesome-icon icon="home"/>
                Home
            </v-btn>
            <v-btn flat
                   v-if="showAdminBoard"
                   :disabled="$route.path === '/admin'"
                   @click="$router.push('/admin')"

            >
                Admin Board
            </v-btn>
            <v-btn flat
                   v-if="showModeratorBoard"
                   :disabled="$route.path === '/mod'"
                   @click="$router.push('/mod')"

            >
                Moderator Board
            </v-btn>
            <v-btn flat
                   v-if="currentUser"
                   :disabled="$route.path === '/user'"
                   @click="$router.push('/user')"

            >
                User
            </v-btn>
            <v-spacer></v-spacer>
            <div v-if="!currentUser">
                <v-btn flat
                       :disabled="$route.path === '/register'"
                       @click="$router.push('/register')"

                >
                    <font-awesome-icon icon="user-plus"/>
                    Sign Up
                </v-btn>
                <v-btn flat
                       :disabled="$route.path === '/login'"
                       @click="$router.push('/login')"

                >
                    <font-awesome-icon icon="sign-in-alt"/>
                    Login
                </v-btn>
            </div>
            <div v-else>
                <v-btn flat
                       :disabled="$route.path === '/profile'"
                       @click="$router.push('/profile')"

                >
                    <font-awesome-icon icon="user"/>
                    {{ currentUser.username }}
                </v-btn>
                <v-btn flat
                       @click="logOut"

                >
                    <font-awesome-icon icon="sign-out-alt"/>
                    LogOut
                </v-btn>
            </div>
        </v-app-bar>


        <v-main>
            <router-view/>
        </v-main>

        <v-footer app>
            <!-- -->
        </v-footer>
    </v-app>

</template>

<script>
    export default {
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            showAdminBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_ADMIN');
                }

                return false;
            },
            showModeratorBoard() {
                if (this.currentUser && this.currentUser.roles) {
                    return this.currentUser.roles.includes('ROLE_MODERATOR');
                }

                return false;
            }
        },
        methods: {
            logOut() {
                this.$store.dispatch('auth/logout');
                this.$router.push('/login');
            }
        },
        beforeMount() {
            if (!this.currentUser) {
                this.$router.replace('/login')
            }
        }
    };
</script>
