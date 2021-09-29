<template>
    <v-app id="app">
        <v-system-bar app>
            <!-- -->
        </v-system-bar>
        <v-app-bar app>
            <!--            <v-toolbar  app>-->
            <v-toolbar-title>BrunisCode</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn flat
                   :disabled="$route.path === '/'|| $route.path === '/home'"
                   @click="$router.push('/')"
                   color="primary"
            >
                <v-icon left>
                    home
                </v-icon>
                Home
            </v-btn>
            <v-btn flat
                   v-if="showAdminBoard"
                   :disabled="$route.path === '/admin'"
                   @click="$router.push('/admin')"
                   color="primary"
            >
                Admin Board
            </v-btn>
            <v-btn flat
                   v-if="showModeratorBoard"
                   :disabled="$route.path === '/mod'"
                   @click="$router.push('/mod')"
                   color="primary"
            >
                Moderator Board
            </v-btn>
            <v-btn flat
                   v-if="currentUser"
                   :disabled="$route.path === '/user'"
                   @click="$router.push('/user')"
                   color="primary"
            >
                User
            </v-btn>
            <div v-if="!currentUser">
                <v-btn flat
                       :disabled="$route.path === '/register'"
                       @click="$router.push('/register')"
                       color="primary"
                >
                    <v-icon left>
                        user-plus
                    </v-icon>
                    Sign Up
                </v-btn>
                <v-btn flat
                       :disabled="$route.path === '/login'"
                       @click="$router.push('/login')"
                       color="primary"
                >
                    <v-icon left>
                        sign-in-alt
                    </v-icon>
                    Login
                </v-btn>
            </div>
            <div v-else>
                <v-btn flat
                       :disabled="$route.path === '/profile'"
                       @click="$router.push('/profile')"
                       color="primary"
                >
                    <v-icon left>
                        user
                    </v-icon>
                    {{ currentUser.username }}
                </v-btn>
                <v-btn flat
                       @click="logOut"
                       color="primary"
                >
                    <v-icon left>
                        sign-out-alt
                    </v-icon>
                    LogOut
                </v-btn>
            </div>
            <!-- -->
            <nav class="navbar navbar-expand navbar-dark bg-dark">
                <a href class="navbar-brand" @click.prevent>BrunisCode</a>
                <div class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <router-link to="/home" class="nav-link">
                            <font-awesome-icon icon="home"/>
                            Home
                        </router-link>
                    </li>
                    <li v-if="showAdminBoard" class="nav-item">
                        <router-link to="/admin" class="nav-link">Admin Board</router-link>
                    </li>
                    <li v-if="showModeratorBoard" class="nav-item">
                        <router-link to="/mod" class="nav-link">Moderator Board</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link v-if="currentUser" to="/user" class="nav-link">User</router-link>
                    </li>
                </div>

                <div v-if="!currentUser" class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link to="/register" class="nav-link">
                            <font-awesome-icon icon="user-plus"/>
                            Sign Up
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/login" class="nav-link">
                            <font-awesome-icon icon="sign-in-alt"/>
                            Login
                        </router-link>
                    </li>
                </div>

                <div v-if="currentUser" class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <router-link to="/profile" class="nav-link">
                            <font-awesome-icon icon="user"/>
                            {{ currentUser.username }}
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href @click.prevent="logOut">
                            <font-awesome-icon icon="sign-out-alt"/>
                            LogOut
                        </a>
                    </li>
                </div>
            </nav>
            <!--            </v-toolbar>-->
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
        }
    };
</script>
